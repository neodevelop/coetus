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

import org.springframework.security.userdetails.UserDetails;

/**
 * 
 * Extends Spring Security's User class to set Grails Domain
 * Class at login, to load auth class from context.
 * 
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public interface GrailsUser extends UserDetails {

	/**
	 * Get the domain object representing the user.
	 * @return  the user
	 */
	GroovyObject getDomainClass();
}
