package org.codehaus.groovy.grails.plugins.springsecurity

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.easymock.EasyMock
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.Authentication
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.ui.logout.LogoutFilter
import org.springframework.security.ui.logout.LogoutHandler

/**
 * Unit tests for <code>LogoutFilterFactoryBean</code>.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class LogoutFilterFactoryBeanTests extends AbstractSecurityTest {

	private final LogoutFilterFactoryBean _factory = new LogoutFilterFactoryBean()

	/**
	 * Test isSingleton.
	 */
	void testIsSingleton() {
		assertTrue _factory.singleton
	}

	/**
	 * Test building a filter.
	 */
	void testFactory() {
		String url = '/after_logout'

		Authentication authentication = authenticate()

		def request1 = new MockHttpServletRequest('GET', '/foo/bar')
		def response1 = new MockHttpServletResponse()
		def request2 = new MockHttpServletRequest('GET', '/j_spring_security_logout')
		def response2 = new MockHttpServletResponse()

		def handlers = []
		(1..5).each { handlers << EasyMock.createMock(LogoutHandler) }
		handlers.each { handler ->
			handler.logout(request2, response2, authentication)
			EasyMock.replay(handler)
		}

		_factory.logoutSuccessUrl = url
		_factory.handlers = handlers
		assertNull _factory.object

		_factory.afterPropertiesSet()

		assertNotNull _factory.object
		assertEquals LogoutFilter, _factory.objectType
		assertTrue _factory.object instanceof LogoutFilter

		// now test the filter to ensure that it calls the handlers

		LogoutFilter filter = _factory.object

		FilterChain chain1 = EasyMock.createMock(FilterChain)
		FilterChain chain2 = EasyMock.createMock(FilterChain)
		chain1.doFilter(request1, response1)
		EasyMock.expectLastCall()
		EasyMock.replay(chain1, chain2)

		// not a logout url, so chain.doFilter() is called
		filter.doFilterHttp(request1, response1, chain1)
		assertNull response1.redirectedUrl 

		filter.doFilterHttp(request2, response2, chain2)
		assertNotNull response2.redirectedUrl

		EasyMock.verify(handlers as Object[])
		EasyMock.verify(chain1, chain2)
	}
}
