package org.grails.plugins.springsecurity.service

import org.codehaus.groovy.grails.plugins.springsecurity.AbstractSecurityTest
import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

import org.springframework.security.Authentication
import org.springframework.security.GrantedAuthority
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.providers.TestingAuthenticationToken

/**
 * Unit tests for AuthenticateServiceTests.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class AuthenticateServiceTests extends AbstractSecurityTest {

	private final AuthenticateService _service = new AuthenticateService()
	private final AuthorizeTools _tools = new AuthorizeTools()
	private final _user = new Object() // domain class instance

	/**
	 * Test transactional.
	 */
	void testTransactional() {
		assertTrue _service.transactional
	}

	/**
	 * Test principal() when authenticated.
	 */
	void testPrincipalAuthenticated() {
		authenticate('role1')
		assertNotNull _service.principal()
	}

	/**
	 * Test principal() when not authenticated.
	 */
	void testPrincipalNotAuthenticated() {
		assertNull _service.principal()
	}

	/**
	 * Test ifAllGranted().
	 */
	void testIfAllGranted() {
		authenticate('role1')
		assertFalse _service.ifAllGranted('role1,role2')

		authenticate('role2,role1')
		assertTrue _service.ifAllGranted('role1,role2')
	}

	/**
	 * Test ifNotGranted().
	 */
	void testIfNotGranted() {
		authenticate('role1')
		assertFalse _service.ifNotGranted('role1,role2')

		authenticate('role3')
		assertTrue _service.ifNotGranted('role1,role2')
	}

	/**
	 * Test ifAnyGranted().
	 */
	void testIfAnyGranted() {
		authenticate('role3')
		assertFalse _service.ifAnyGranted('role1,role2')

		authenticate('role1')
		assertTrue _service.ifAnyGranted('role1,role2')
	}

	/**
	 * Test userDomain() when not authenticated.
	 */
	void testUserDomainNotAuthenticated() {
		assertNull _service.userDomain()
	}

	/**
	 * Test userDomain() when authenticated.
	 */
	void testUserDomainAuthenticated() {
		authenticate('role1')
		assertEquals _user, _service.userDomain()
	}

	/**
	 * Test passwordEncoder().
	 */
	void testPasswordEncoder() {
		def config = [security: [algorithm: 'SHA', encodeHashAsBase64: false]]
		_service.metaClass.getSecurityConfig = { -> config }

		assertEquals '7c6a61c68ef8b9b6b061b28c348bc1ed7921cb53', _service.passwordEncoder('passw0rd')

		config.security.encodeHashAsBase64 = true
		assertEquals 'N2M2YTYxYzY4ZWY4YjliNmIwNjFiMjhjMzQ4YmMxZWQ3OTIxY2I1Mw==',
			_service.passwordEncoder('passw0rd').toString()

		config.security.algorithm = 'SHA-256'

		config.security.encodeHashAsBase64 = false
		assertEquals '8f0e2f76e22b43e2855189877e7dc1e1e7d98c226c95db247cd1d547928334a9',
			_service.passwordEncoder('passw0rd')

		config.security.encodeHashAsBase64 = true
		assertEquals 'OGYwZTJmNzZlMjJiNDNlMjg1NTE4OTg3N2U3ZGMxZTFlN2Q5OGMyMjZjOTVkYjI0N2NkMWQ1\nNDc5MjgzMzRhOQ==',
			_service.passwordEncoder('passw0rd').toString()
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
		removeMetaClassMethods(AuthenticateService)
	}
}
