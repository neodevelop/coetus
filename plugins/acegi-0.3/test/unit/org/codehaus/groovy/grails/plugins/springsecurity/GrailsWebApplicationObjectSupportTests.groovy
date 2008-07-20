package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.hibernate.SessionFactory
import org.hibernate.classic.Session
import org.springframework.orm.hibernate3.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager
import org.springframework.web.context.WebApplicationContext

/**
 * Unit tests for GrailsWebApplicationObjectSupport.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsWebApplicationObjectSupportTests extends GroovyTestCase {

	private final Thing _thing = new Thing()

	/**
	 * Test setUpSession() when there's one existing already.
	 */
	void testSetUpSessionExisting() {

		WebApplicationContext context = EasyMock.createMock(WebApplicationContext)
		_thing.setApplicationContext(context)

		SessionFactory sessionFactory = EasyMock.createMock(SessionFactory)
		EasyMock.expect(context.getBean('sessionFactory')).andReturn(sessionFactory)

		Session session = EasyMock.createMock(Session)

		EasyMock.replay(context, sessionFactory, session)

		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session))

		GrailsWebApplicationObjectSupport.SessionContainer container = _thing.setUpSession()
		assertEquals session, container.session
		assertTrue container._existingSession

		EasyMock.verify(context, sessionFactory, session)
	}

	/**
	 * Test setUpSession() when there's not one existing already.
	 */
	void testSetUpSessionNew() {

		WebApplicationContext context = EasyMock.createMock(WebApplicationContext)
		_thing.setApplicationContext(context)

		SessionFactory sessionFactory = EasyMock.createMock(SessionFactory)
		EasyMock.expect(context.getBean('sessionFactory')).andReturn(sessionFactory)

		Session session = EasyMock.createMock(Session)
		EasyMock.expect(sessionFactory.openSession()).andReturn(session)
		EasyMock.expect(session.getSessionFactory()).andReturn(sessionFactory)

		EasyMock.replay(context, sessionFactory, session)

		assertFalse TransactionSynchronizationManager.hasResource(sessionFactory)

		GrailsWebApplicationObjectSupport.SessionContainer container = _thing.setUpSession()
		assertEquals session, container.session
		assertFalse container._existingSession

		assertTrue TransactionSynchronizationManager.hasResource(sessionFactory)

		EasyMock.verify(context, sessionFactory, session)
	}
}

class Thing extends GrailsWebApplicationObjectSupport {
	// concrete class to allow access to non-abstract methods
}
