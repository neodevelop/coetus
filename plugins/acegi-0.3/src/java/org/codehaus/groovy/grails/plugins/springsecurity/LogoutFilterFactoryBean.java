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
package org.codehaus.groovy.grails.plugins.springsecurity;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.ui.logout.LogoutFilter;
import org.springframework.security.ui.logout.LogoutHandler;

/**
 * Configures a <code>LogoutFilter</code> given a list of <code>LogoutHandler</code>s.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public class LogoutFilterFactoryBean implements FactoryBean, InitializingBean {

	private List<LogoutHandler> _handlers;
	private LogoutFilter _logoutFilter;
	private String _logoutSuccessUrl;

	/**
	 * {@inheritDoc}
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public LogoutFilter getObject() {
		return _logoutFilter;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	public Class<LogoutFilter> getObjectType() {
		return LogoutFilter.class;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	public boolean isSingleton() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() {
		_logoutFilter = new LogoutFilter(
				_logoutSuccessUrl,
				_handlers.toArray(new LogoutHandler[_handlers.size()])) {
			@Override
			protected void sendRedirect(
					final HttpServletRequest request,
					final HttpServletResponse response,
					final String url) throws IOException {
				RedirectUtils.sendRedirect(request, response, url);
			}
		};
	}

	/**
	 * Dependency injection for the logout success url.
	 * @param logoutSuccessUrl  the url
	 */
	@Required
	public void setLogoutSuccessUrl(final String logoutSuccessUrl) {
		_logoutSuccessUrl = logoutSuccessUrl;
	}

	/**
	 * Dependency injection for the handlers.
	 * @param handlers  the handlers
	 */
	@Required
	public void setHandlers(final List<LogoutHandler> handlers) {
		_handlers = handlers;
	}
}
