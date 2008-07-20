package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.AccessDeniedException

/**
 * Unit tests for GrailsAccessDeniedHandlerImpl.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsAccessDeniedHandlerImplTests extends AbstractSecurityTest {

	private final GrailsAccessDeniedHandlerImpl _handler = new GrailsAccessDeniedHandlerImpl()
	private final MockHttpServletRequest _request = new MockHttpServletRequest('GET', '/foo/bar')
	private final MockHttpServletResponse _response = new MockHttpServletResponse()
	private final String _message = 'denied'
	private final AccessDeniedException _e = new AccessDeniedException(_message)


	/**
	 * Test handle() when there's no error page set.
	 */
	void testHandleNoErrorPage() {

		_handler.handle(_request, _response, _e)

		assertEquals 403, _response.status
		assertEquals _message, _response.errorMessage
	}

	/**
	 * Test handle() when there's an error page set.
	 */
	void testHandleErrorPage() {

		_handler.errorPage = '/error'

		_handler.handle(_request, _response, _e)

		assertEquals 'http://localhost/error', _response.redirectedUrl
	}

	/**
	 * Test handle() when there's an error page set and the server port isn't 80.
	 */
	void testHandleErrorPageNonstandardPort() {

		_handler.errorPage = '/error'
		_request.serverPort = 90

		_handler.handle(_request, _response, _e)

		assertEquals 'http://localhost:90/error', _response.redirectedUrl
	}

	/**
	 * Test handle() when there's an error page set and the request is secure.
	 */
	void testHandleErrorPageSecure() {

		_handler.errorPage = '/error'
		_request.scheme = 'https'

		_handler.handle(_request, _response, _e)

		assertEquals 'https://localhost/error', _response.redirectedUrl
	}

	/**
	 * Test handle() when there's an error page set, the request is secure, and the port isn't 443.
	 */
	void testHandleErrorPageSecureNonstandardPort() {

		_handler.errorPage = '/error'
		_request.scheme = 'https'
		_request.serverPort = 9443

		_handler.handle(_request, _response, _e)

		assertEquals 'https://localhost:9443/error', _response.redirectedUrl
	}

	/**
	 * Test handle() when there's an Ajax error page set.
	 */
	void testHandleAjaxErrorPage() {

		_handler.ajaxErrorPage = '/errorAjax'
		_request.addHeader(WithAjaxAuthenticationProcessingFilterEntryPoint.AJAX_HEADER, 'XHR')

		_handler.handle(_request, _response, _e)

		assertEquals 'http://localhost/errorAjax', _response.redirectedUrl
	}

	/**
	 * Test handle() for Ajax request when there's no Ajax error page set.
	 */
	void testHandleAjaxNoErrorPage() {

		_request.addHeader(WithAjaxAuthenticationProcessingFilterEntryPoint.AJAX_HEADER, 'XHR')

		_handler.handle(_request, _response, _e)

		assertEquals 403, _response.status
		assertEquals _message, _response.errorMessage
	}

	/**
	 * Test setErrorPage().
	 */
	void testSetErrorPage() {
		shouldFail(IllegalArgumentException) {
			_handler.errorPage = 'foo'
		}

		_handler.errorPage = '/foo'
	}

	/**
	 * Test setAjaxErrorPage().
	 */
	void testSetAjaxErrorPage() {
		shouldFail(IllegalArgumentException) {
			_handler.ajaxErrorPage = 'foo'
		}

		_handler.ajaxErrorPage = '/foo'
	}
}
