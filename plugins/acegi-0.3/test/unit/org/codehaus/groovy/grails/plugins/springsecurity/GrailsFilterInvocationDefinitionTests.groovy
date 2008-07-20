package org.codehaus.groovy.grails.plugins.springsecurity

import javax.servlet.FilterChain

import org.easymock.EasyMock
import org.hibernate.Query
import org.hibernate.Session
import org.springframework.security.ConfigAttributeDefinition
import org.springframework.security.intercept.web.FilterInvocation
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

/**
 * Unit tests for GrailsUserImpl.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsFilterInvocationDefinitionTests extends AbstractSecurityTest {

	private final GrailsFilterInvocationDefinition _definition = new GrailsFilterInvocationDefinition()

	/**
	 * Test getConfigAttributeDefinitions().
	 */
	void testGetConfigAttributeDefinitions() {
		assertNull _definition.configAttributeDefinitions
	}

	/**
	 * Test supports().
	 */
	void testSupports() {
		assertTrue _definition.supports(FilterInvocation)
	}

	/**
	 * Test getAttributes() with null argument.
	 */
	void testGetAttributesNull() {
		shouldFail(IllegalArgumentException) {
			assertTrue _definition.getAttributes(null)
		}
	}

	/**
	 * Test getAttributes() with wrong type.
	 */
	void testGetAttributesWrongType() {
		shouldFail(IllegalArgumentException) {
			assertTrue _definition.getAttributes('foo')
		}
	}

	/**
	 * Test getAttributes().
	 */
	void testGetAttributes() {

		_definition.requestMapPathFieldName = 'url'
		_definition.requestMapConfigAttributeField = 'configAttribute'

		String role = 'role1'
		String url = '/foo/bar'

		Session session = EasyMock.createMock(Session)

		GrailsWebApplicationObjectSupport.SessionContainer container =
 			new GrailsWebApplicationObjectSupport.SessionContainer(session, true)

		// replace definition to avoid going to the database
		_definition.metaClass.setUpSession = { -> container }

		MockHttpServletRequest request = new MockHttpServletRequest()
		request.setServletPath(url)
		MockHttpServletResponse response = new MockHttpServletResponse()
		FilterChain chain = EasyMock.createMock(FilterChain)

		Query query = EasyMock.createMock(Query)
		EasyMock.expect(session.createQuery((String)EasyMock.anyObject())).andReturn(query)

		def reqmaps = [[url: url, configAttribute: role]]
		EasyMock.expect(query.list()).andReturn(reqmaps)

		EasyMock.replay(session, chain, query)

		FilterInvocation fi = new FilterInvocation(request, response, chain)
		ConfigAttributeDefinition cad = _definition.getAttributes(fi)

		assertEquals 1, cad.configAttributes.size()
		assertEquals role, cad.configAttributes[0].attribute

		EasyMock.verify(session, chain, query)
	}

	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() {
		super.tearDown()
		removeMetaClassMethods(GrailsFilterInvocationDefinition)
	}
}
