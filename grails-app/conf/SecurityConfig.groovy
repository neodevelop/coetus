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
security {

	active = true

	// change to true to use OpenID authentication
	useOpenId = false

	// change to true and specify parameters to use LDAP/ActiveDirectory authentication
	useLdap = false
	//ldapServer = 'ldap://localhost:389' // 'ldap://ad.example.com', 'ldap://monkeymachine:389/dc=acegisecurity,dc=org'
	//ldapManagerDn = 'cn=admin,dc=example,dc=com'
	//ldapManagerPassword = 'secret'
	//ldapSearchBase = 'dc=example,dc=com' // 'ou=users,dc=example,dc=com'
	//ldapSearchFilter = '(uid={0})' //, '(mailNickname={0})'
	//ldapGroupSearchBase = 'ou=groups,dc=example,dc=com'
	//ldapGroupSearchFilter = 'uniquemember={0}'

	algorithm = 'MD5' 
	//use Base64 text ( true or false )
	encodeHashAsBase64 = false
	errorPage = "/nopermission.gsp"

	/** login user domain class name and fields */
	loginUserDomainClass = "Person"
	userName = 'username'
	password = 'passwd'
	enabled = 'enabled'
	relationalAuthorities = 'authorities'

	/*
	 * You can specify method to retrieve the roles. (you need to set relationalAuthorities = null)
	 */
	// getAuthoritiesMethod = null //'getMoreAuthorities'

	/**
	 * Authority domain class authority field name
	 * authorityFieldInList
	 */
	authorityDomainClass = "Authority"
	authorityField = 'authority'

	/** use RequestMap from DomainClass */
	useRequestMapDomainClass = true
	/** Requestmap domain class (if useRequestMapDomainClass = true) */
	requestMapClass = "Requestmap"
	requestMapPathField = 'url'
	requestMapConfigAttributeField = 'configAttribute'

	/** 
	 * if useRequestMapDomainClass is false, set request map pattern in string
	 * see example below
	 */

	/*
	requestMapString = """
		CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
		PATTERN_TYPE_APACHE_ANT

		/login/**=IS_AUTHENTICATED_ANONYMOUSLY
		/admin/**=ROLE_USER
		/book/test/**=IS_AUTHENTICATED_FULLY
		/book/**=ROLE_SUPERVISOR
		/**=IS_AUTHENTICATED_ANONYMOUSLY
	"""
	*/

	/**
	 * To use email notification for user registration, set the following userMail to
	 * true and config your mail settings.Note you also need to run the script
	 * grails generate-registration.
	 */
	useMail = false
	//mailHost = 'mail.gentejava.org'
	//mailUsername = 'user@localhost'
	//mailPassword = 'sungod'
	//mailProtocol = 'smtp'
	//mailFrom = 'domingo.suarez@gmail.com'

	/** AJAX request header */
	//ajaxHeader = 'X-Requested-With'
  
	/** default user's role for user registration */
	defaultRole = 'ROLE_USER'

	/** use basicProcessingFilter */
	basicProcessingFilter = false
	/** use switchUserProcessingFilter */
	switchUserProcessingFilter = false
}
