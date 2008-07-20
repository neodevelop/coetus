package org.codehaus.groovy.grails.plugins.springsecurity.ldap

import org.grails.plugins.springsecurity.service.AuthenticateService
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsDaoImpl
import org.codehaus.groovy.grails.plugins.springsecurity.ldap.GrailsLdapUser

import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.GrantedAuthority
import org.springframework.security.userdetails.UserDetails
import org.springframework.security.userdetails.ldap.LdapUserDetails
import org.springframework.security.userdetails.ldap.LdapUserDetailsMapper

/**
 * Extends the default to return a <code>GrailsLdapUser</code> implementing
 * both <code>GrailsUser</code> and <code>LdapUserDetails</code>.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsLdapUserDetailsMapper extends LdapUserDetailsMapper {

	/**
	 * Dependency injection for <code>GrailsDaoImpl</code>.
	 */
	def GrailsDaoImpl grailsDaoImpl

	/**
	 * Dependency injection for the authentication service.
	 */
	def AuthenticateService authenticateService

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.userdetails.ldap.LdapUserDetailsMapper#mapUserFromContext(
	 * 	org.springframework.ldap.core.DirContextOperations, java.lang.String,
	 * 	org.springframework.security.GrantedAuthority[])
	 */
	@Override
	UserDetails mapUserFromContext(DirContextOperations ctx, String username, GrantedAuthority[] authorities) {

		boolean retrieveDatabaseRoles = authenticateService.securityConfig.security.ldapRetrieveDatabaseRoles
		def dbDetails = grailsDaoImpl.loadUserByUsername(username, retrieveDatabaseRoles)
		authorities = mergeDatabaseRoles(dbDetails, authorities)

		LdapUserDetails ldapDetails = (LdapUserDetails)super.mapUserFromContext(ctx, username, authorities)
		return new GrailsLdapUser(ldapDetails, dbDetails.domainClass)
	}

	private GrantedAuthority[] mergeDatabaseRoles(details, GrantedAuthority[] authorities) {
		def merged = []
		if (authorities) {
			merged.addAll(authorities as List)
		}

		if (details.authorities) {
			merged.addAll(details.authorities as List)
		}

		return merged as GrantedAuthority[]
	}
}
