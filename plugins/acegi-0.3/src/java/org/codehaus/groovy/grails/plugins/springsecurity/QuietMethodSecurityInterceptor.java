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

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.intercept.InterceptorStatusToken;
import org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor;

/**
 * MethodSecurityInterceptor without throws Exceptions if Method Access is
 * denied returns null
 * 
 * @author T.Yamamoto
 */
public class QuietMethodSecurityInterceptor extends MethodSecurityInterceptor {

	private boolean throwException;
	private Exception lastException;

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor#invoke(
	 * 	org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(final MethodInvocation mi) throws Throwable {
		Object result = null;
		InterceptorStatusToken token = null;
		try {
			token = super.beforeInvocation(mi);
		}
		catch (Exception e) {
			lastException = e;
			if (throwException) {
				throw e;
			}
			logger.error(e.getMessage());
			return null;
		}

		try {
			result = mi.proceed();
		}
		catch (Exception e) {
			lastException = e;
			if (throwException) {
				throw e;
			}
			logger.error(e.getMessage());
			return null;
		}

		try {
			result = super.afterInvocation(token, result);
		}
		catch (Exception e) {
			lastException = e;
			if (throwException) {
				throw e;
			}
			logger.error(e.getMessage());
			return null;
		}

		return result;
	}

	/**
	 * For testing.
	 * @return  the most recent exception, if any.
	 */
	/*package*/ Exception getLastException() {
		return lastException;
	}

	/**
	 * Dependency injection for throw exception flag.
	 * @param throwException  if <code>true</code> throw exceptions, otherwise just log
	 */
	public void setThrowException(final boolean throwException) {
		this.throwException = throwException;
	}
}
