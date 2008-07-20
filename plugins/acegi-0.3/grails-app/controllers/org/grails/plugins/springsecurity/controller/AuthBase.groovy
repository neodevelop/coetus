/* Copyright 2006-2007 the original author or authors.
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
package org.grails.plugins.springsecurity.controller

import org.grails.plugins.springsecurity.service.AuthenticateService

import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.web.servlet.support.RequestContextUtils as RCU

/**
 * [Example] Controllers Base class for to use Spring Security (authentication and authorization).
 * Usage: class SomeController extends AuthBase { }
 * @author T.Yamamoto
 */
class AuthBase {

	/** Authenticate Service */
	AuthenticateService authenticateService

	/** Login user class */
	def loginUser

	/** is user logged on or not */
	boolean logon

	/** principal */
	def authPrincipal

	/** is Admin */
	boolean isAdmin

	/** Locale */
	Locale locale

	/** main request permission setting */
	def requestAllowed

	def beforeInterceptor = {
		if (requestAllowed != null && !authenticateService.ifAnyGranted(requestAllowed)) {
			println 'request not allowed: ' + requestAllowed
			redirect(uri: '/')
		 	return
		}

		authPrincipal = SCH?.context?.authentication?.principal
		if (authPrincipal != null && authPrincipal != 'anonymousUser') {
			loginUser = authPrincipal?.domainClass
			logon = true
			isAdmin = authenticateService.ifAnyGranted('ROLE_SUPERVISOR')
		}

		/* i18n: if lang params */
		if (params['lang']) {
			locale = new Locale(params['lang'])
			RCU.getLocaleResolver(request).setLocale(request,response,locale)
			session.lang = params['lang']
		}
		/* need this for jetty */
		if (session.lang != null) {
			locale = new Locale(session.lang)
			RCU.getLocaleResolver(request).setLocale(request,response,locale)
		}
		if (locale == null) {
			locale = RCU.getLocale(request)
		}

		/* cache */
		response.setHeader('Cache-Control','no-cache') // HTTP 1.1
		response.setDateHeader('max-age', 0) 
		response.setIntHeader ('Expires', -1) //prevents caching at the proxy server 
		response.addHeader('cache-Control', 'private') //IE5.x only
	}
}
