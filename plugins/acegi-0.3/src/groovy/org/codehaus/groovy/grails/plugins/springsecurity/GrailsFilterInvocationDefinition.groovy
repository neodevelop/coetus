/*
 * Copyright 2008 the original author or authors.
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
package org.codehaus.groovy.grails.plugins.springsecurity

import org.apache.log4j.Logger

import org.springframework.security.ConfigAttributeDefinition
import org.springframework.security.SecurityConfig
import org.springframework.security.intercept.web.FilterInvocation
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource
import org.springframework.util.AntPathMatcher
import org.springframework.util.StringUtils

/**
 * GrailsFilterInvocationDefinition.
 * 
 * @author Tsuyoshi Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsFilterInvocationDefinition
extends GrailsWebApplicationObjectSupport
implements FilterInvocationDefinitionSource {

	private final Logger logger = Logger.getLogger(getClass())
	private final pathMatcher = new AntPathMatcher()

	def requestMapClass // 'Requestmap'
	def requestMapPathFieldName // url
	def requestMapConfigAttributeField // 'configAttribute'

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
	 */
	Collection<ConfigAttributeDefinition> getConfigAttributeDefinitions() {
		return null
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#getAttributes(java.lang.Object)
	 */
	ConfigAttributeDefinition getAttributes(final Object object) throws IllegalArgumentException {
		if (object == null || !supports(object.getClass())) {
			logger.error('Object must be a FilterInvocation')
			throw new IllegalArgumentException('Object must be a FilterInvocation')
		}

		return lookupAttributes(object.requestUrl)
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
	 */
	boolean supports(final Class clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz)
	}

	private ConfigAttributeDefinition lookupAttributes(String url) {

		GrailsWebApplicationObjectSupport.SessionContainer container = setUpSession()

		try {
			url = url.toLowerCase()

			int pos = url.indexOf('?')
			if (pos > 0) {
				url = url.substring(0, pos)
			}

			url = url.replaceAll("\"|'", '')
			url = url.replaceAll('//', '/')

			if (!url.contains('.') || url.contains('.gsp') || url.contains('.jsp')) {
				String hql = "from $requestMapClass where $requestMapPathFieldName = '/' or $requestMapPathFieldName = '/**' "
				String path = '/'
				for (StringTokenizer stn = new StringTokenizer(url, '/'); stn.hasMoreTokens();) {
					path += stn.nextToken() + '/'
			        hql += "or lower($requestMapPathFieldName) like '$path%' "
			        path += '/'
				}
				hql += "order by length($requestMapPathFieldName) desc"

				def query = container.session.createQuery(hql)
				for (reqmap in query/*.setCacheable(true)*/.list()) {
					if (pathMatcher.match(reqmap."$requestMapPathFieldName", url)) {
						String configAttrs = reqmap."$requestMapConfigAttributeField"
						if (configAttrs.endsWith(',')) {
							configAttrs = configAttrs.substring(0, configAttrs.length() - 2)
						}
						return new ConfigAttributeDefinition(
								StringUtils.commaDelimitedListToStringArray(configAttrs))
					}
				}
			}
		}
		finally {
			releaseSession(container)
		}

		return null
	}
}
