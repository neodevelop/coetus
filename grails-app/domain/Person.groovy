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

/**
 * Person for user account.
 */
class Person {
	static searchable = true
	static transients = ['pass']
	static hasMany = [authorities: Authority]
	static belongsTo = Authority

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	Boolean enabled = true

	String email
	Boolean canBeContactedViaEmail = false
	Boolean notifyMeAttendees = true

	/** description */
	String description = ''
	
	String company
	String blog
	String geolocation
	Date dateCreated
	Date lastUpdated

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		username(blank: false, unique: true,size:1..30)
		userRealName(blank: true,size:0..200)
		company(nullable:true,blank:true,size:0..100)
		geolocation(nullable:true,blank:true,size:0..200)
		passwd(blank: false,size:0..300)
		email(blank:false,email:true,unique:true)
		description(nullable:true,blank:true,size:0..1000)
		blog(nullable:true,blank:true,url:true,size:0..300)
		enabled()
		notifyMeAttendees(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
	}
	
	static mapping = {
		tablePerHierarchy false
		cache false
	}
	
	def hasLocation() {
		return location?.length() > 0;
	}
	
	def mapLocation() {
		if(hasLocation()) {
			def l = location.split(",")
			return ["latitude":l[0], "longitude":l[1]]
		}
		return null;
	}
	
	def llatitude() {
		return mapLocation()["latitude"]
	}
	def llongitude() {
		return mapLocation()["longitude"]
	}
	
	
	
	String toString() { "${username} - ${userRealName}" }
}
