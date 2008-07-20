package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.springframework.security.GrantedAuthority
import org.springframework.security.GrantedAuthorityImpl

/**
 * Unit tests for GrailsUserImpl.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsUserImplTests extends AbstractSecurityTest {

	/**
	 * Simple test of the constructor.
	 */
	void testConstructor() {
		
		String username = 'the_username'
		String password = 'the_password'
		boolean enabled = true
		boolean accountNonExpired = false
		boolean credentialsNonExpired = true
		boolean accountNonLocked = false
		GrantedAuthority[] authorities = [
			new GrantedAuthorityImpl('role1'),
			new GrantedAuthorityImpl('role2'),
			new GrantedAuthorityImpl('role3')] as GrantedAuthority[]
		def user = EasyMock.createMock(GroovyObject)

		GrailsUserImpl grailsUser = new GrailsUserImpl(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities, user)

		assertEquals username, grailsUser.username
		assertEquals password, grailsUser.password
		assertEquals enabled, grailsUser.enabled
		assertEquals accountNonExpired, grailsUser.accountNonExpired
		assertEquals credentialsNonExpired, grailsUser.credentialsNonExpired
		assertEquals accountNonLocked, grailsUser.accountNonLocked
		assertArrayEquals authorities, grailsUser.authorities
		assertEquals user, grailsUser.domainClass
	}
}
