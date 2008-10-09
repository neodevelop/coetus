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
class CreateController {
	def eventService
	def authenticateService
	def index = {redirect(action:event)}
	def event = {
		[event:new Event()]
	}
	def saveEvent = {
		def event = new Event()
		event.properties = params
		event.createdBy = Person.get(authenticateService.userDomain().id)
		try {
			eventService.create event
			redirect(controller:"event", action:"show", id:event.id)
		} catch (Throwable t) {
			flash.message = 'Error en el registro del evento'
			render(view: 'event', model: [event: event])
			//redirect(controller:"create", action:"saveEvent", event:event)
			//return
		}
		
		
	}
}
