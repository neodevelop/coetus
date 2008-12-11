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
class EventController {
	def scaffold = true
	def addTalkForm = {
		def event = Event.get(params.id)
		if(event) {
			render(template:"addTalkForm",model:[event:event])
		} else {
			render "No se encontro el evento"
		}
	}
	def addTalkToEvent = {
		println params.eventId
		def talk = new Talk(params)
		def event = Event.get(params.eventId)
		talk.event = event
		
		event.addToTalk(talk)
        
        def l = Event.activeEvents()

		event.save()
		
		if(event.hasErrors()) {
			throw new RuntimeException("")
		}
		if(event.save()) {
			log.info "Guarde un evento"
		} else {
			throw new RuntimeException("")
		}
		
		
		if(talk.hasErrors()) {
			throw new RuntimeException("")
		}
		if(talk.save()) {
			log.info "Guarde un evento"
		} else {
			throw new RuntimeException("")
		}
		
		
		talk.save()
		redirect(action:"show",id:params.eventId)
	}
}