/*
 * Copyright 2007 the original author or authors.
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
package org.codehaus.groovy.grails.plugins.springsecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilterEntryPoint;

/**
 * AuthenticationProcessingFilterEntryPoint with Ajax login form option if
 * Method Access is denied returns <code>null</code>.
 *
 * @author T.Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public class WithAjaxAuthenticationProcessingFilterEntryPoint extends AuthenticationProcessingFilterEntryPoint {

	/**
	 * Default value for the name of the Ajax header.
	 */
	public static final String AJAX_HEADER = "X-Requested-With";

	private String ajaxLoginFormUrl;
	private String ajaxHeader = AJAX_HEADER;

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.ui.webapp.AuthenticationProcessingFilterEntryPoint#determineUrlToUseForThisRequest(
	 * 	javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * 	org.springframework.security.AuthenticationException)
	 */
	@Override
	protected String determineUrlToUseForThisRequest(
			final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) {

		if (request.getHeader(ajaxHeader) != null && ajaxLoginFormUrl != null) {
			return ajaxLoginFormUrl;
		}

		return getLoginFormUrl();
	}

	/**
	 * Dependency injection for the Ajax login form url, e.g. '/login/authAjax'.
	 * @param url  the url
	 */
	public void setAjaxLoginFormUrl(final String url) {
		if (url != null && !url.startsWith("/")) {
			throw new IllegalArgumentException("ajaxLoginFormUrl must begin with '/'");
		}
		ajaxLoginFormUrl = url;
	}

	/**
	 * Dependency injection for the Ajax header name; defaults to 'X-Requested-With'.
	 * @param header  the header name
	 */
	public void setAjaxHeader(final String header) {
		ajaxHeader = header;
	}
}
