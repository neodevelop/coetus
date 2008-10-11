/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.grails.plugins.springsecurity.service.AuthenticateService

import org.springframework.security.providers.UsernamePasswordAuthenticationToken as AuthToken
import org.springframework.security.context.SecurityContextHolder as SCH

import org.grails.mail.MailService

/**
 * Actions over Person object.
 */
class RegisterController {

	def mailService
	def authenticateService
	def daoAuthenticationProvider
	def recaptchaService

	def allowedMethods = [save: 'POST', update: 'POST']

	/**
	 * User Registration Top page
	 */
	def index = {
		def person = new Person()
		//if logon user.
		if (authenticateService.userDomain()) {
			log.info("${authenticateService.userDomain()} user hit the register page")
			redirect(uri: '/me')
			return
		} else {
			return [person: person]
		}

		if (session.id) {
			person.properties = params
			return [person: person]
		}

		redirect(uri: '/')
	}

	/**
	 * User Information page for current user.
	 */
	def show = {

		//get user id from session's domain class.
		def user = authenticateService.userDomain()
		if (user) {
			render(view: 'show', model: [person: Person.get(user.id)])
		}
		else {
			redirect(action: 'index')
		}
	}

	/**
	 * Edit page for current user.
	 */
	def edit = {

		def person
		def user = authenticateService.userDomain()
		if (user) {
			person = Person.get(user.id)
		}

		if (!person) {
			flash.message = "[Illegal Access] User not found with id ${params.id}"
			redirect(action: 'index')
			return
		}

		[person: person]
	}

	/**
	 * update action for current user's edit page
	 */
	def update = {

		def person
		def user = authenticateService.userDomain()
		if (user) {
			person = Person.get(user.id)
		}
		else {
			redirect(action: 'index')
			return
		}

		if (!person) {
			flash.message = "[Illegal Access] User not found with id ${params.id}"
			redirect(action: 'index', id: params.id)
			return
		}

		//if user want to change password. leave passwd field blank, passwd will not change.
		if (params.passwd && params.passwd.length() > 0
				&& params.repasswd && params.repasswd.length() > 0) {
			if (params.passwd == params.repasswd) {
				person.passwd = authenticateService.passwordEncoder(params.passwd)
			}
			else {
				person.passwd = ''
				flash.message = 'The passwords you entered do not match.'
				render(view: 'edit', model: [person: person])
				return
			}
		}

		person.userRealName = params.userRealName
		person.email = params.email
		if (params.canBeContactedViaEmail) {
			person.canBeContactedViaEmail = true
		}
		else {
			person.canBeContactedViaEmail = false
		}

		if (person.save()) {
			redirect(action: 'show', id: person.id)
		}
		else {
			render(view: 'edit', model: [person: person])
		}
	 }

	/**
	 * Person save action.
	 */
	def save = {

		if (authenticateService.userDomain() != null) {
			log.info("${authenticateService.userDomain()} user hit the register page")
			redirect(controller:"person",action:"show",params:[person:Person.get(authenticateService.userDomain().id)])
		}

		def person = new Person()
		person.properties = params
		def manager = params.manager

		def config = authenticateService.securityConfig
		def defaultRole = config.security.defaultRole

		def role = Authority.findByAuthority(defaultRole)
		if (!role) {
			person.passwd = ''
			flash.message = 'Default Role not found.'
			render(view: 'index', model: [person: person])
			return 
		}

		def recaptchaOK = true
		if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
        	recaptchaOK = false
			flash.message = 'Access code did not match.'
			render(view: 'index', model: [person: person])
			return
        }
		        

		if (params.passwd != params.repasswd) {
			person.passwd = ''
			flash.message = 'The passwords you entered do not match.'
			render(view: 'index', model: [person: person])
			return
		}

		def pass = authenticateService.passwordEncoder(params.passwd)
		person.passwd = pass
		person.enabled = true
		person.canBeContactedViaEmail = true
		if (person.save()) {
			role.addToPeople(person)
			
			if(manager) {
				def roleManager = Authority.findByAuthority("ROLE_MANAGER")
				if(roleManager) {
					roleManager.addToPeople(person)
				}
			}
			
			sendAccountInfo(person, params.passwd)

			person.save(flush: true)

			def auth = new AuthToken(person.username, params.passwd)
			def authtoken = daoAuthenticationProvider.authenticate(auth)
			SCH.context.authentication = authtoken
			//Recaptcha
			recaptchaService.cleanUp(session)
			flash.message = "register.ready"
			redirect(controller:"person",action:"show",id:person.id)
		}
		else {
			person.passwd = ''
			render(view: 'index', model: [person: person])
		}
	}
	
	def forgot = {
		
	}
	def recovery = {
		Person person = Person.findByEmail(params.email)
		if(person != null){
			//Regenerate pass & save new pass
			def newPasswd = ""
			def charArray = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9']
			8.times{
				newPasswd+= charArray[(Math.random()*charArray.size).toInteger()]
			}
			person.passwd = authenticateService.passwordEncoder(newPasswd)
			person.save()
			//Send new pass
			sendAccountInfo(person, newPasswd)
			
			flash.message="person.passwd.sendit"
			flash.defaultMessage="It has send it a new password in the email provided..."
			render(view:'forgot')
		}else{
			person = new Person()
			person.errors.rejectValue('email','person.email.notFound')
			render(view:'forgot',model:[person:person])
		}
	}
	
	def sendAccountInfo(person, password) {
		try {
			mailService.sendMail {
				to person.email
				from "neodevelop@gmail.com"
				subject "[Coetus] Account Info"
				body """
You have signed up for an account at:

${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}

Here are the details of your account:
-------------------------------------
LoginName: ${person.username}
Email: ${person.email}
Full Name: ${person.userRealName}
Password: ${password}
"""
			}
		} catch (Throwable t) {
			log.error "Error sending email"
		}
		
	}
}
