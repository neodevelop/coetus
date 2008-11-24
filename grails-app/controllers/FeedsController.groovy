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

import groovy.xml.MarkupBuilder

class FeedsController {
	def index = { redirect(uri:"/") }
	
	/**
	 * 
	 */
	private def buildEventsFeed = { fTitle, fLink, fDescription, events ->
		render(feedType:"rss", feedVersion:"2.0") {
			title = fTitle
			link = "${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/feeds/${fLink}"
			description = fDescription
			events.each() { event ->
				entry(event.name) {
					link = "${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/events/detail/${event.id}"
					if(event.dateCreated) { publishedDate = event.dateCreated }
					if(event.createdBy?.userRealName) { author = event.createdBy.userRealName }
					if(event.lastUpdated) { updatedDate = event.lastUpdated }
					title = event.name
					event.description
				}
			}
		}
	}
	
	def allEvents = {
		buildEventsFeed("Coetus", "allEvents", "All events in Coetus", Event.activeEvents(20))
	}
	
	def eventsByOrganizer = {
		def organizer = Organizer.get(params.id)
		if(organizer) {
			buildEventsFeed("Coetus", "eventsByOrganizer/${params.id}", "Eventos de ${organizer.name}", Event.activeEventsByOrganizer(organizer, 20))
		} else {
			redirect(uri:"/")
		}
	}
}
