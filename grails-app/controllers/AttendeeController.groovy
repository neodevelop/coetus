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
class AttendeeController {
    def scaffold = true

	def byEvent = {
		def attendeeList = null
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			attendeeList = null
		} else {
			attendeeList = Attendee.findAllByEvent(event)
		}
		
		render(view: 'list', model: [attendeeList: attendeeList])
	}
	
	def testAjax = {
		println "Hola desde el server"
		render "Hola desde el server"
	}
}