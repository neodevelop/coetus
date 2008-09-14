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
	boolean enabled = true

	String email
	boolean canBeContactedViaEmail = false

	/** description */
	String description = ''
	
	String company
	String blog

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		username(blank: false, unique: true,size:1..30)
		userRealName(blank: false,size:1..200)
		company(nullable:true,blank:true,size:0..100)
		passwd(blank: false,size:0..300)
		email(blank:false,email:true,unique:true)
		description(nullable:true,blank:true,size:0..1000)
		blog(nullable:true,blank:true,url:true,size:0..300)
		enabled()
	}
	
	static mapping = {
		tablePerHierarchy false
	}
	
	String toString() { "${username} - ${userRealName}" }
}
