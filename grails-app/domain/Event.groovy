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

class Event {
	String name
	String description
	Date startTime = new Date()
	Date endTime = new Date()
	Boolean allDay = false
	String location
	Boolean necessaryRegistry = false
	Status status = Status.PLANNING
	Boolean showStatus = true
	
	static hasMany = [talks:Talk]
	
	static constraints = {
		name(blank:false,size:5..200)
		description(blank:false,size:5..2000)
		startTime(nullable:false)
		endTime(nullable:false)
		allDay(nullable:false)
		location(blank:false,size:5..200)
		necessaryRegistry(nullable:false)
	}
	
	String toString() { "${name}" }
}
