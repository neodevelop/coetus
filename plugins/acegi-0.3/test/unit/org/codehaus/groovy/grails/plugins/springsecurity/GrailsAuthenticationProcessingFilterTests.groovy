package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

/**
 * Unit tests for GrailsAuthenticationProcessingFilter.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsAuthenticationProcessingFilterTests extends AbstractSecurityTest {

	/**
	 * Test sendRedirect().
	 */
	void testSendRedirect() {
		MockHttpServletRequest request = new MockHttpServletRequest('GET', '/foo/bar')
		MockHttpServletResponse response = new MockHttpServletResponse()
		GrailsAuthenticationProcessingFilter filter = new GrailsAuthenticationProcessingFilter()

		String url = '/foo/bar'

		filter.sendRedirect(request, response, url)

		assertEquals 'http://localhost/foo/bar', response.redirectedUrl
	}
}
