package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.springframework.security.GrantedAuthority
import org.springframework.security.GrantedAuthorityImpl

/**
 * Unit tests for AuthorizeTools.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class AuthorizeToolsTests extends AbstractSecurityTest {

	private final AuthorizeTools _tools = new AuthorizeTools()

	/**
	 * Test authoritiesToRoles().
	 */
	void testAuthoritiesToRoles() {

		def roleNames = []
		def authorities = []
		(1..10).each { i ->
			String name = "role${i}"
			roleNames << name
			authorities << new GrantedAuthorityImpl(name)
		}

		def roles = _tools.authoritiesToRoles(authorities)
		assertSameContents roleNames, roles
	}

	/**
	 * Test authoritiesToRoles() when there is an authority with a null string.
	 */
	void testAuthoritiesToRolesNullAuthority() {

		def authority = EasyMock.createMock(GrantedAuthority)
		EasyMock.expect(authority.getAuthority()).andReturn(null)
		EasyMock.replay(authority)
		def authorities = [new GrantedAuthorityImpl('role1'), authority]

		shouldFail(IllegalArgumentException) {
			_tools.authoritiesToRoles(authorities)
		}

		EasyMock.verify(authority)
	}

	/**
	 * Test getPrincipalAuthorities() when not authenticated.
	 */
	void testGetPrincipalAuthoritiesNoAuth() {
		assertTrue _tools.getPrincipalAuthorities().empty
	}

	/**
	 * Test getPrincipalAuthorities() when not authenticated.
	 */
	void testGetPrincipalAuthoritiesNoRoles() {
		authenticate()
		assertTrue _tools.getPrincipalAuthorities().empty
	}

	/**
	 * Test getPrincipalAuthorities().
	 */
	void testGetPrincipalAuthorities() {
		def authorities = []
		(1..10).each { i ->
			authorities << new GrantedAuthorityImpl("role${i}")
		}

		authenticate(null, null, authorities as GrantedAuthority[])

		assertEquals authorities, _tools.getPrincipalAuthorities()
	}

	/**
	 * Test parseAuthoritiesString().
	 */
	void testParseAuthoritiesString() {
		String roleNames = 'role1,role2,role3'
		def roles = _tools.parseAuthoritiesString(roleNames)

		assertEquals 3, roles.size()
		def expected = ['role1', 'role2', 'role3']
		def actual = roles.collect { authority -> authority.authority }
		assertSameContents expected, actual
	}

	/**
	 * Test retainAll().
	 */
	void testRetainAll() {
		def granted = [new GrantedAuthorityImpl('role1'),
		               new GrantedAuthorityImpl('role2'),
		               new GrantedAuthorityImpl('role3')]
		def required = [new GrantedAuthorityImpl('role1')]

		def expected = ['role1']
		assertSameContents expected, _tools.retainAll(granted, required)
	}

	/**
	 * Test rolesToAuthorities().
	 */
	void testRolesToAuthorities() {
		def grantedRoles = ['role1', 'role2', 'role3']

		def granted = [new GrantedAuthorityImpl('role1'),
		               new GrantedAuthorityImpl('role2'),
		               new GrantedAuthorityImpl('role4')]

		def expected = ['role1', 'role2']
		assertSameContents expected, _tools.rolesToAuthorities(grantedRoles, granted)
	}

	/**
	 * Check that two collections contain the same data, independent of collection class and order.
	 */
	private void assertSameContents(c1, c2) {
		assertEquals c1.size(), c2.size()
		assertTrue c1.containsAll(c2)
	}
}
