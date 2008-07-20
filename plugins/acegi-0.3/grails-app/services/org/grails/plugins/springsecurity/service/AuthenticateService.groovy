/* Copyright 2006-2007 the original author or authors.
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
package org.grails.plugins.springsecurity.service

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex
import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.ui.AbstractProcessingFilter

/**
 * Rewrote to the Groovy from Java source of
 * org.acegisecurity.taglibs.authz.AuthorizeTag
 * for to use from the controllers and a taglib.
 *
 * @author T.Yamamoto
 */
class AuthenticateService {

	boolean transactional = true

	private securityConfig

	/**
	 * @deprecated You can invoke tags from controllers (since grails-0.6)
   	*/
   	def ifAllGranted(role) {
		def granted = AuthorizeTools.getPrincipalAuthorities()
		return granted.containsAll(AuthorizeTools.parseAuthoritiesString(role))
	}

	/**
   	 * @deprecated You can invoke tags from controllers (since grails-0.6)
   	 */
   	def ifNotGranted(role) {

		def granted = AuthorizeTools.getPrincipalAuthorities()
		Set grantedCopy = AuthorizeTools.retainAll(granted, AuthorizeTools.parseAuthoritiesString(role));
		return grantedCopy.isEmpty()
	}

	/**
	 * @deprecated You can invoke tags from controllers (since grails-0.6)
	 */
	def ifAnyGranted(role) {

		def granted = AuthorizeTools.getPrincipalAuthorities()
		Set grantedCopy = AuthorizeTools.retainAll(granted, AuthorizeTools.parseAuthoritiesString(role))
		return !grantedCopy.isEmpty()
	}

	/**
	 * Get the currently logged in user's principal.
	 * @return  the principal or <code>null</code> if not logged in
	 */
	def principal() {
		return SCH?.context?.authentication?.principal
	}

	/**
	 * Get the currently logged in user's domain class.
	 * @return  the domain object or <code>null</code> if not logged in
	 */
	def userDomain() {

		def principal = principal()
		def loginUser = null
		if (principal != null && principal != 'anonymousUser') {
			loginUser = principal?.domainClass
		}
		return loginUser
	}

	/**
	 * Load the security configuration.
	 * @return  the config
	 */
	ConfigObject getSecurityConfig() {
		if (securityConfig == null) {
			GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader())
			def userConfig = new ConfigSlurper().parse(classLoader.loadClass('SecurityConfig'))
			def defaultConfig = new ConfigSlurper().parse(classLoader.loadClass('DefaultSecurityConfig'))
			securityConfig = defaultConfig.merge(userConfig)
		}
		return securityConfig
	}

	/**
	 * returns a MessageDigest password. 
	 * (changes algorithm method dynamically by param of config)
	 */
	def passwordEncoder(String passwd) {

		def securityConfig = getSecurityConfig()

		def algorithm = securityConfig.security.algorithm
		def encodeHashAsBase64 = securityConfig.security.encodeHashAsBase64

		def encoded = new String(Hex.encodeHex(MessageDigest.getInstance(algorithm).digest(passwd.bytes)))

		if (encodeHashAsBase64) {
			return encoded.bytes.encodeBase64()
		}

		return encoded
	}

	/**
	 * Check if the request was triggered by an Ajax call.
	 * @param request  the request
	 * @return  <code>true</code> if Ajax
	 */
	boolean isAjax(request) {

		// look for an ajax=true parameter
		if ('true' == request.getParameter('ajax')) {
			return true
		}

		// check the current request's headers
		def ajaxHeader = getSecurityConfig().security.ajaxHeader
		if (request.getHeader(ajaxHeader) != null) {
			return true
		}

		// check the SavedRequest's headers
		def savedRequest = request.session[AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY]
		if (savedRequest) {
			return savedRequest.getHeaderValues(ajaxHeader).hasNext()
		}

		return false
	}
}
