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

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.ui.AccessDeniedHandler;
import org.springframework.security.util.PortResolver;
import org.springframework.security.util.PortResolverImpl;

/**
 * AccessDeniedHandler for redirect to errorPage (not RequestDispatcher#forward).
 *
 * @author T.Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public class GrailsAccessDeniedHandlerImpl implements AccessDeniedHandler {

	private String errorPage;
	private String ajaxErrorPage;
	private String ajaxHeader = WithAjaxAuthenticationProcessingFilterEntryPoint.AJAX_HEADER;
	private PortResolver portResolver = new PortResolverImpl();

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.ui.AccessDeniedHandler#handle(
	 * 	javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 * 	org.springframework.security.AccessDeniedException)
	 */
	public void handle(final ServletRequest req, final ServletResponse res, final AccessDeniedException e)
			throws IOException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		if (errorPage != null || (ajaxErrorPage != null && request.getHeader(ajaxHeader) != null)) {
			boolean includePort = true;
			String scheme = request.getScheme();
			String serverName = request.getServerName();
			int serverPort = portResolver.getServerPort(request);
			String contextPath = request.getContextPath();
			boolean inHttp = "http".equals(scheme.toLowerCase());
			boolean inHttps = "https".equals(scheme.toLowerCase());

			if (inHttp && (serverPort == 80)) {
				includePort = false;
			}
			else if (inHttps && (serverPort == 443)) {
				includePort = false;
			}

			String commonRedirectUrl = scheme + "://" + serverName + ((includePort) ? (":" + serverPort) : "")
					+ contextPath;
			String redirectUrl = commonRedirectUrl;
			if (ajaxErrorPage != null && request.getHeader(ajaxHeader) != null) {
				redirectUrl += ajaxErrorPage;
			}
			else if (errorPage != null) {
				redirectUrl += errorPage;
			}
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			}

			response.sendRedirect(response.encodeRedirectURL(redirectUrl));
		}

		if (!response.isCommitted()) {
			// Send 403 (we do this after response has been written)
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}

	/**
	 * Dependency injection for the error page, e.g. '/login/denied'.
	 * @param page  the page
	 */
	public void setErrorPage(final String page) {
		if (page != null && !page.startsWith("/")) {
			throw new IllegalArgumentException("ErrorPage must begin with '/'");
		}
		errorPage = page;
	}

	/**
	 * Dependency injection for the Ajax error page, e.g. '/login/deniedAjax'.
	 * @param page  the page
	 */
	public void setAjaxErrorPage(final String page) {
		if (page != null && !page.startsWith("/")) {
			throw new IllegalArgumentException("ErrorPage must begin with '/'");
		}
		ajaxErrorPage = page;
	}

	/**
	 * Dependency injection for the Ajax header name; defaults to 'X-Requested-With'.
	 * @param header  the header name
	 */
	public void setAjaxHeader(final String header) {
		ajaxHeader = header;
	}
}
