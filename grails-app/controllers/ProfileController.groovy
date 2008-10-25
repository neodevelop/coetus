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
class ProfileController {
	def authenticateService
	def index = {
		redirect(action: show, params: params)
	}
	def show = {
		[person:Person.get(authenticateService.userDomain()?.id)]
	}
	def edit = {
		[person:Person.get(authenticateService.userDomain()?.id)]
	}
	def update = {
		def person = Person.get(authenticateService.userDomain()?.id)
		if(person) {
			person.properties = params
			if(!person.hasErrors() && person.save(flush:true)) {
				flash.message = "person.info.updated"
				flash.args = [person.username]
				flash.defaultMessage = "User : ${person.username} updated successful...!!!"
				redirect(uri: '/me')
			}
			else {
				render(view:'my',model:[person:person])
			}
		}
    }
}
