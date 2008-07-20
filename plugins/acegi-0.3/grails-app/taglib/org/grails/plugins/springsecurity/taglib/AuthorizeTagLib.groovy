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
package org.grails.plugins.springsecurity.taglib

import org.springframework.security.context.SecurityContextHolder as SCH

import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

/**
 * Authorize Taglibs.
 * Rewritten in Groovy from Java source of org.acegisecurity.taglibs.authz.AuthorizeTag.
 *
 * @author T.Yamamoto
 */
class AuthorizeTagLib {

	/**
	 * <g:ifAllGranted role="ROLE_USER,ROLE_ADMIN,ROLE_SUPERVISOR">
	 *  All the listed roles must be granted for the tag to output its body.
	 * </g:ifAllGranted>
	 */
	def ifAllGranted = { attrs, body ->
		def role = attrs.role
		def granted = AuthorizeTools.getPrincipalAuthorities()

		if (granted.containsAll(AuthorizeTools.parseAuthoritiesString(role))) {
			out << body()
		}
	}

	/**
	 * <g:ifNotGranted role="ROLE_USER,ROLE_ADMIN,ROLE_SUPERVISOR">
	 *  None of the listed roles must be granted for the tag to output its body.
	 * </g:ifNotGranted>
	 */
	def ifNotGranted = { attrs, body ->
		def role = attrs.role
		def granted = AuthorizeTools.getPrincipalAuthorities()
		Set grantedCopy = AuthorizeTools.retainAll(granted, AuthorizeTools.parseAuthoritiesString(role))
		if (grantedCopy.empty) {
			out << body()
		}
	}

	/**
	 * <g:ifAnyGranted role="ROLE_USER,ROLE_ADMIN,ROLE_SUPERVISOR">
	 *  Any of the listed roles must be granted for the tag to output its body.
	 * </g:ifAnyGranted>
	 */
	def ifAnyGranted = { attrs, body ->

		def role = attrs.role
		def granted = AuthorizeTools.getPrincipalAuthorities()
		Set grantedCopy = AuthorizeTools.retainAll(granted, AuthorizeTools.parseAuthoritiesString(role))
		if (!grantedCopy.empty) {
			out << body()
		}
	}

	/**
	 * <g:loggedInUserInfo field="userRealName">Guest User</g:loggedInUserInfo>
	 */
	def loggedInUserInfo = { attrs, body ->
		if (isAuthenticated()) {
			out << SCH.context.authentication.principal.domainClass?."${attrs.field}"
		}
		else {
			out << body()
		}
	}

	def isLoggedIn = { attrs, body ->
		if (isAuthenticated()) {
			out << body()
		}
	}

	def isNotLoggedIn = {attrs, body ->
		if (!isAuthenticated()) {
			out << body()
		}
	}

	def loggedInUsername = { attrs ->
		if (isAuthenticated()) {
			out << SCH.context.authentication.principal.username
		}
	}

	private boolean isAuthenticated() {
		def authPrincipal = SCH?.context?.authentication?.principal
		return authPrincipal != null && authPrincipal != 'anonymousUser'
	}
}
