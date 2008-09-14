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
class SetupCoetusController {
	def index = {
		if(Authority.count() == 0) {
			new Authority(description:"Usuario del Sistema", authority:"ROLE_USER").save()
			new Authority(description:"Administrador del Sistema", authority:"ROLE_ADMIN").save()
			new Authority(description:"Orador", authority:"ROLE_SPEAKER").save()
		}
		
		if(Requestmap.count() == 0) {
			//new Requestmap(url:"/event/**", configAttribute:"ROLE_ADMIN").save()
			//new Requestmap(url:"/events/**", configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/myevents/**", configAttribute:"ROLE_USER").save()
			//new Requestmap(url:"/person/**", configAttribute:"ROLE_ADMIN").save()
			//new Requestmap(url:"/role/**", configAttribute:"ROLE_ADMIN").save()
			//new Requestmap(url:"/talk/**", configAttribute:"ROLE_ADMIN").save()
			//new Requestmap(url:"/user/**", configAttribute:"ROLE_ADMIN").save()
		}
	}
}
