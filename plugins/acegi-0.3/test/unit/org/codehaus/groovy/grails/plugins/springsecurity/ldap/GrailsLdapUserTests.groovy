package org.codehaus.groovy.grails.plugins.springsecurity.ldap

import javax.naming.directory.Attributes

import org.codehaus.groovy.grails.plugins.springsecurity.AbstractSecurityTest

import org.springframework.security.GrantedAuthority
import org.springframework.security.GrantedAuthorityImpl
import org.springframework.security.userdetails.ldap.LdapUserDetails

/**
 * Unit tests for GrailsLdapUserTests.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsLdapUserTests extends AbstractSecurityTest {

	/**
	 * Test the constructor.
	 */
	void testConstructor() {

		String dn = 'dn'
		GrantedAuthority[] authorities =
			[new GrantedAuthorityImpl('role1'), new GrantedAuthorityImpl('role2')] as GrantedAuthority[]
		String password = 'passw0rd'
		String username = 'username'
		boolean accountNonExpired = true
		boolean accountNonLocked = false
		boolean credentialsNonExpired = true
		boolean enabled = false

		LdapUserDetails details = new TestLdapUserDetails(
			dn: dn, authorities: authorities, password: password,
			username: username, accountNonExpired: accountNonExpired,
			accountNonLocked: accountNonLocked,
			credentialsNonExpired: credentialsNonExpired, enabled: enabled)

		def domainClass = new Expando()
		GrailsLdapUser user = new GrailsLdapUser(details, domainClass)

		assertEquals dn, user.dn
		assertArrayEquals authorities, user.authorities
		assertEquals password, user.password
		assertEquals username, user.username
		assertEquals accountNonExpired, user.accountNonExpired
		assertEquals accountNonLocked, user.accountNonLocked
		assertEquals credentialsNonExpired, user.credentialsNonExpired
		assertEquals enabled, user.enabled
		assertEquals domainClass, user.domainClass
	}
}

class TestLdapUserDetails implements LdapUserDetails {
	Attributes attributes
	String dn
	GrantedAuthority[] authorities
	String password
	String username
	boolean accountNonExpired
	boolean accountNonLocked
	boolean credentialsNonExpired
	boolean enabled
}
