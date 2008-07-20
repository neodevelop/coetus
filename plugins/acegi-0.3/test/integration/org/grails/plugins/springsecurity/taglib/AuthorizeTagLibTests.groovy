package org.grails.plugins.springsecurity.taglib

import grails.test.GroovyPagesTestCase

import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

import org.springframework.security.Authentication
import org.springframework.security.GrantedAuthority
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.providers.TestingAuthenticationToken

/**
 * Integration tests for <code>AuthorizeTagLib</code>.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class AuthorizeTagLibTests extends GroovyPagesTestCase {

	private final AuthorizeTools _tools = new AuthorizeTools()
	private final Expando _user = new Expando()

	def transactional = false

	/**
	 * Test ifAllGranted().
	 */
	void testIfAllGranted() {
		String body = 'the_content'

		authenticate('role1')
		assertOutputEquals '', "<g:ifAllGranted role='role1,role2'>${body}</g:ifAllGranted>"

		authenticate('role2,role1')
		assertOutputEquals body, "<g:ifAllGranted role='role1,role2'>${body}</g:ifAllGranted>"
	}

	/**
	 * Test ifNotGranted().
	 */
	void testIfNotGrantedMissingRole() {
		String body = 'the_content'

		authenticate('role1')
		assertOutputEquals '', "<g:ifNotGranted role='role1,role2'>${body}</g:ifNotGranted>"

		authenticate('role3')
		assertOutputEquals body, "<g:ifNotGranted role='role1,role2'>${body}</g:ifNotGranted>"
	}

	/**
	 * Test ifAnyGranted().
	 */
	void testIfAnyGranted() {
		String body = 'the_content'

		authenticate('role3')
		assertOutputEquals '', "<g:ifAnyGranted role='role1,role2'>${body}</g:ifAnyGranted>"

		authenticate('role2')
		assertOutputEquals body, "<g:ifAnyGranted role='role1,role2'>${body}</g:ifAnyGranted>"
	}

	/**
	 * Test isLoggedIn().
	 */
	void testIsLoggedInTrue() {
		String body = 'the_content'

		assertOutputEquals '', "<g:isLoggedIn role='role1,role2'>${body}</g:isLoggedIn>"

		authenticate('role1')
		assertOutputEquals body, "<g:isLoggedIn role='role1,role2'>${body}</g:isLoggedIn>"
	}

	/**
	 * Test isNotLoggedIn().
	 */
	void testIsNotLoggedIn() {
		String body = 'the_content'

		assertOutputEquals body, "<g:isNotLoggedIn role='role1,role2'>${body}</g:isNotLoggedIn>"

		authenticate('role1')
		assertOutputEquals '', "<g:isNotLoggedIn role='role1,role2'>${body}</g:isNotLoggedIn>"
	}

	/**
	 * Test loggedInUserInfo().
	 */
	void testLoggedInUserInfo() {
		String fullName = 'First Last'
		_user.fullName = fullName

		assertOutputEquals '', "<g:loggedInUserInfo field='fullName'/>"

		authenticate('role1')
		assertOutputEquals fullName, "<g:loggedInUserInfo field='fullName'/>"
	}

	private void authenticate(roles) {

		def principal = new Expando()
		principal.domainClass = _user

		Authentication authentication = new TestingAuthenticationToken(
				principal, null, _tools.parseAuthoritiesString(roles) as GrantedAuthority[])
		authentication.authenticated = true
		SCH.context.authentication = authentication
	}

	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() {
		super.tearDown()
		SCH.context.authentication = null
	}
}
