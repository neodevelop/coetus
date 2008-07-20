package org.codehaus.groovy.grails.plugins.springsecurity.ldap;

import groovy.lang.GroovyObject;

import javax.naming.directory.Attributes;

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser;
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserImpl;
import org.springframework.security.userdetails.ldap.LdapUserDetails;

/**
 * A <code>GrailsUser</code> for use in LDAP authentication.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
public class GrailsLdapUser extends GrailsUserImpl implements GrailsUser, LdapUserDetails {

	private static final long serialVersionUID = -1557817722745366207L;

	private final Attributes _attributes;
	private final String _dn;

	/**
	 * Full constructor.
	 * @param details  the original details
	 * @param domainClass  the domain instance
	 */
	@SuppressWarnings("deprecation") // just passing along the core impl
	public GrailsLdapUser(final LdapUserDetails details, final GroovyObject domainClass) {
		super(details.getUsername(), details.getPassword(), details.isEnabled(), details.isAccountNonExpired(), details.isCredentialsNonExpired(), details.isAccountNonLocked(), details.getAuthorities(), domainClass);
		_attributes = details.getAttributes();
		_dn = details.getDn();
	}

	/**
	 * {@inheritDoc}
	 */
	public Attributes getAttributes() {
		return _attributes;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.userdetails.ldap.LdapUserDetails#getDn()
	 */
	public String getDn() {
		return _dn;
	}
}
