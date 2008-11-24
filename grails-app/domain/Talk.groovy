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

class Talk {
	static searchable = true
	
	Event event
	String title
	String summary
	Date startDate = new Date()
	Date endDate = new Date()
	String location
	Boolean necessaryRegistry = false
	Integer capacity = 0
	Boolean displayCapacity = false
	Status status = Status.PLANNING
	Boolean showStatus = true
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = Person
	static hasMany = [speakers:Person]
	
	static constraints = {
		event()
		title(blank:false,size:1..200)
		summary(blank:false,size:1..2000)
		startDate()
		endDate()
		location(blank:false,size:1..100)
		necessaryRegistry(nullable:false)
		capacity()
		displayCapacity()
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
	}
	
	static mapping = {
		speakers column:'talk_Id',joinTable:'TALKS_SPEAKERS'
	}
	
	String toString() { title }
}
