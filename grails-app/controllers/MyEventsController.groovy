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
import org.springframework.security.context.SecurityContextHolder as SCH

class MyEventsController {
	
	def index = { redirect(uri:"/") }

    private boolean isAuthenticated() {
		def authPrincipal = SCH?.context?.authentication?.principal
		return authPrincipal != null && authPrincipal != 'anonymousUser'
	}
	def authenticateService

    def myDetail = {
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		
		def userName = SCH.context.authentication.principal.username
		def user = Person.findByUsername(userName)
		def attendee = Attendee.findByPersonAndEvent(user, event)
		def talksPendingToAttend = [] //Talk.findAllByEvent(event)
		event.talks.each() {
			if(!attendee.talks.contains(it)) {
				talksPendingToAttend.add(it)
			}
		}
		[event: event, attendee:attendee, talksPendingToAttend:talksPendingToAttend]
	}
	
	def register = {
		def event = Event.get(params.id)
		
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		
		if(event.status != Status.OPEN) {
			flash.message = "event.not.open"
            flash.args = [event.status]
            flash.defaultMessage = "Can not register in a non open event. The event status is {0}"
			return
		}
		
		def userName = SCH.context.authentication.principal.username
		def user = Person.findByUsername(userName)
		
		def attendee = Attendee.findByPersonAndEvent(user, event)
		if(attendee) {
			flash.message = "attendee.found"
            flash.args = [userName, event.name]
            flash.defaultMessage = "El usuario {0} ya esta registrdo al evento {1}"
			return
		}
		
		[event: event]
	}
	
	def sendEventRegisterInfo(person, event) {
		try {
			sendMail {
				to person.email
				from "coetus@synergyj.com"
				subject "[Coetus] Register Event Info"
				body """
You have successful register to a Event:

Here are the details of your register:
-------------------------------------
Full Name: ${person.userRealName}
Event: ${event.name}
Date: ${event.startTime}

Full event details at:
${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/events/detail/${event.id}

Thanks and Enjoy
--
Staff SpringHispano.org
"""
			}
		} catch (Throwable t) {
			log.error "Error sending email"
			t.printStackTrace()
		}
		
	}
	
	def doRegistry ={
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		} else {
		}
		
		def cc = []
		
		params.each{ k, v-> 
			if(k.startsWith("talk_")) {
				def t = k.substring(k.lastIndexOf("_") + 1)
				cc.add(t)
			}
		}
				
		def tals = []
		
		def principal = authenticateService.principal()
		def attendee = new Attendee()
		//println attendee
		
		def person = Person.findByUsername(principal.getUsername())
		if(!person) {
			flash.message = "person.not.found"
            flash.args = [principal.getUserName]
            flash.defaultMessage = "Person not found with name {0}"
			return
		} else {
			println person
			println event
			attendee = new Attendee(person:person, event:event)
			attendee.save()
			println "Guarde el asistente"
			println attendee
		}
		
		cc.each() {
			def c = Talk.get(it)
			println c
			attendee.addToTalks(c)
			println "agregue la charla a la asistencia"
		}
		println attendee
		attendee.save()
		
		flash.message = "registration.successful"
        flash.defaultMessage = "Your registration is successful"
		sendEventRegisterInfo(person, event)
		
		redirect(action:"myDetail", id:event.id)
	}
	
	def deleteTalk = {
		def talk = Talk.get(params.talkId)
		def event = Event.get(params.eventId)
		
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		
		def userName = SCH.context.authentication.principal.username
		def user = Person.findByUsername(userName)
		
		def attendee = Attendee.findByPersonAndEvent(user, event)
		
		attendee.removeFromTalks(talk)
		attendee.save()
		
		redirect(action:"myDetail", id:event.id)
	}
	
	def addTalkToAttend = {
		def event = Event.get(params.id)
		
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		
		def userName = SCH.context.authentication.principal.username
		def user = Person.findByUsername(userName)
		
		def attendee = Attendee.findByPersonAndEvent(user, event)
		
		def cc = []
		
		params.each{ k, v-> 
			if(k.startsWith("talk_")) {
				def t = k.substring(k.lastIndexOf("_") + 1)
				cc.add(t)
			}
		}
				
		def tals = []
		
		cc.each() {
			def c = Talk.get(it)
			println c
			attendee.addToTalks(c)
		}
		
		attendee.save(flush:true)
		redirect(action:"myDetail", id:event.id)s
	}
}
