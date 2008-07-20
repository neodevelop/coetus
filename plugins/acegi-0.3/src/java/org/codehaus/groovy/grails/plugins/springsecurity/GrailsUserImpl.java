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
package org.codehaus.groovy.grails.plugins.springsecurity;

import groovy.lang.GroovyObject;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

/**
 * Extends Spring Security's User class to set Grails Domain Class at login,
 * to load auth class from context.
 *
 * @author T.Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public class GrailsUserImpl extends User implements GrailsUser {

	private static final long serialVersionUID = 6089520028447407158L;

	private final GroovyObject domainClass;

	/**
	 * Constructor.
	 * @param username the username presented to the
	 *        <code>DaoAuthenticationProvider</code>
	 * @param password the password that should be presented to the
	 *        <code>DaoAuthenticationProvider</code>
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param accountNonExpired set to <code>true</code> if the account has not
	 *        expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials
	 *        have not expired
	 * @param accountNonLocked set to <code>true</code> if the account is not
	 *        locked
	 * @param authorities the authorities that should be granted to the caller
	 *        if they presented the correct username and password and the user
	 *        is enabled
	 * @param user  the user domain instance
	 *
	 * @throws IllegalArgumentException if a <code>null</code> value was passed
	 *         either as a parameter or as an element in the
	 *         <code>GrantedAuthority[]</code> array
	 */
	public GrailsUserImpl(
			final String username, final String password, final boolean enabled,
			final boolean accountNonExpired, final boolean credentialsNonExpired,
			final boolean accountNonLocked, final GrantedAuthority[] authorities,
			final GroovyObject user) throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		domainClass = user;
	}

	/**
	 * {@inheritDoc}
	 * @see org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser#getDomainClass()
	 */
	public GroovyObject getDomainClass() {
		return domainClass;
	}
}
