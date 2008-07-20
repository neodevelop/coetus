package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.AuthenticationException

/**
 * Unit tests for WithAjaxAuthenticationProcessingFilterEntryPoint.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class WithAjaxAuthenticationProcessingFilterEntryPointTests extends AbstractSecurityTest {

	private final WithAjaxAuthenticationProcessingFilterEntryPoint _entryPoint =
		new WithAjaxAuthenticationProcessingFilterEntryPoint()

	private String _loginFormUrl = '/loginFormUrl'
	private String _ajaxLoginFormUrl = '/ajaxLoginFormUrl'

	/**
	 * Test commence() with Ajax false.
	 */
	void testCommenceNotAjax() {

		MockHttpServletRequest request = new MockHttpServletRequest()
		MockHttpServletResponse response = new MockHttpServletResponse()

		_entryPoint.commence(request, response, null)

		assertEquals _loginFormUrl, response.forwardedUrl
	}

	/**
	 * Test commence() with Ajax true.
	 */
	void testCommenceAjax() {

		MockHttpServletRequest request = new MockHttpServletRequest()
		MockHttpServletResponse response = new MockHttpServletResponse()

		_entryPoint.ajaxHeader = 'ajax_header'
		request.addHeader('ajax_header', 'XHR')

		_entryPoint.commence(request, response, null)

		assertEquals _ajaxLoginFormUrl, response.forwardedUrl
	}

	/**
	 * Test setAjaxLoginFormUrl().
	 */
	void testSetAjaxLoginFormUrl() {
		shouldFail(IllegalArgumentException) {
			_entryPoint.ajaxLoginFormUrl = 'foo'
		}

		_entryPoint.ajaxLoginFormUrl = '/foo'
	}

	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() {
		super.setUp()
		_entryPoint.serverSideRedirect = true
		_entryPoint.loginFormUrl = _loginFormUrl
		_entryPoint.ajaxLoginFormUrl = _ajaxLoginFormUrl
	}
}
