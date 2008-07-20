/*
 * Copyright 2008 the original author or authors.
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
package org.codehaus.groovy.grails.plugins.springsecurity

import org.springframework.security.GrantedAuthority
import org.springframework.security.GrantedAuthorityImpl
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.util.StringUtils as STU

/**
 * Helper methods.
 * @author Tsuyoshi Yamamoto
 */
class AuthorizeTools {

	/**
	 * Extract the role names from authorities.
	 * @param  authorities  the authorities
	 * @return  the names
	 */
	static Set<String> authoritiesToRoles(authorities) {
		def roles = new HashSet()
		authorities.each { authority ->
			if (null == authority.authority) {
				throw new IllegalArgumentException(
						"Cannot process GrantedAuthority objects which return null from getAuthority() - attempting to process "
						+ authority)
			}
			roles.add(authority.authority)
		}

		return roles
	}

	/**
	 * Get the current user's authorities.
	 * @return  a list of authorities (empty if not authenticated).
	 */
	static def getPrincipalAuthorities() {
		def currentUser = SCH.context.authentication
		if (null == currentUser) {
			return Collections.EMPTY_LIST
		}

		if (null == currentUser.authorities || currentUser.authorities.length == 0) {
			return Collections.EMPTY_LIST
		}

		return Arrays.asList(currentUser.authorities)
	}

	/**
	 * Split the role names and create <code>GrantedAuthority</code>s for each.
	 * @param authorizationsString  comma-delimited role names
	 * @return authorities (possibly empty)
	 */
	static Set<GrantedAuthority> parseAuthoritiesString(String authorizationsString) {
		def requiredAuthorities = new HashSet()
		def authorities = STU.commaDelimitedListToStringArray(authorizationsString)
		authorities.each { auth ->
			requiredAuthorities.add(new GrantedAuthorityImpl(auth))
		}

		return requiredAuthorities
	}

	static Set<String> retainAll(granted, required) {
		def grantedRoles = authoritiesToRoles(granted)
		def requiredRoles = authoritiesToRoles(required)
		grantedRoles.retainAll(requiredRoles)

		return rolesToAuthorities(grantedRoles, granted)
	}

	static Set<String> rolesToAuthorities(grantedRoles, granted) {
		def target = new HashSet()
		grantedRoles.each { role ->
			def auth = granted.find { authority -> authority.authority == role }
			if (auth != null) {
				target.add(auth.authority)
			}
		}

		return target
	}
}
