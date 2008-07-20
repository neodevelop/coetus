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

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * Grails Web Application Object Support.
 *
 * @author T.Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public abstract class GrailsWebApplicationObjectSupport extends WebApplicationObjectSupport {

	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * Holds the session created or existing session and a flag indicating whether it was
	 * existing (so we know whether to close it or not).
	 */
	public static class SessionContainer {
		private final Session _session;
		private final boolean _existingSession;

		private SessionContainer(final Session session, final boolean existingSession) {
			_session = session;
			_existingSession = existingSession;
		}

		/**
		 * Get the session.
		 * @return  the session
		 */
		public Session getSession() {
			return _session;
		}
	}

	/**
	 * Set up hibernate session.
	 * @return  the session container, which holds the session and a boolean indicating if the session was pre-existing
	 */
	protected SessionContainer setUpSession() {
		SessionFactory sessionFactory = getSessionFactory();

		Session session;
		boolean existing;
		if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
			logger.debug("Session already has transaction attached");
			existing = true;
			session = ((SessionHolder)TransactionSynchronizationManager.getResource(sessionFactory)).getSession();
		}
		else {
			logger.debug("Session does not have transaction attached... Creating new one");
			existing = false;
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			SessionHolder sessionHolder = new SessionHolder(session);
			TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
		}

		return new SessionContainer(session, existing);
	}

	/**
	 * Release Session.
	 */
	protected void releaseSession(final SessionContainer session) {
		if (session._existingSession) {
			return;
		}

		SessionFactory sessionFactory = getSessionFactory();
		SessionHolder sessionHolder = (SessionHolder)TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.releaseSession(sessionHolder.getSession(), sessionFactory);
		logger.debug("Session released");
	}

	private SessionFactory getSessionFactory() {
		return (SessionFactory)getWebApplicationContext().getBean("sessionFactory");
	}
}
