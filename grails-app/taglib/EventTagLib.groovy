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
import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

class EventTagLib {
	
	private boolean isAuthenticated() {
		def authPrincipal = SCH?.context?.authentication?.principal
		return authPrincipal != null && authPrincipal != 'anonymousUser'
	}
	
	def currentEvents = { attrs ->
		out <<  '<h3>'
		out <<  attrs.title
		out <<  '</h3> <div class="content"> <ul class="linklist">'
		
		for(event in Event.findAllByStatus(Status.OPEN)) {
			out << '<li> <a href="'
			out << request.contextPath
			out << '/events/detail/'
			out << event.id
			out << '">'
			out << event.name
			out << '</a> </li>'
		}
		out << '</ul> </div>'
	}
	
	def myEvents = { attrs ->
		if (isAuthenticated()) {
			out <<  '<h3>'
			out <<  attrs.title
			out <<  '</h3> <div class="content"> <ul class="linklist">'
			
			def userName = SCH.context.authentication.principal.username
			def user = Person.findByUsername(userName)
			def attendees = Attendee.findByPerson(user)
			
			if(!attendees) {
				out << 'Sin eventos'
			} else {
				for(attendee in attendees) {
					out << '<li> <a href="'
					out << request.contextPath
					out << '/myEvents/myDetail/'
					out << attendee.event.id
					out << '">'
					out << attendee.event.name
					out << '</a> </li>'
				}
			}
			out << '</ul> </div>'
		}
	}	
}