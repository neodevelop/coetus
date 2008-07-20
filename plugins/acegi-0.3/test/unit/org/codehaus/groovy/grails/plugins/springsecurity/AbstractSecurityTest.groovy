package org.codehaus.groovy.grails.plugins.springsecurity

import org.springframework.security.Authentication
import org.springframework.security.GrantedAuthority
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.providers.TestingAuthenticationToken

/**
 * Abstract base class for security unit tests.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
abstract class AbstractSecurityTest extends GroovyTestCase {

	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() {
		super.tearDown()
		SCH.context.authentication = null
	}

	/**
	 * Register a currently authenticated user.
	 * @return  the authentication
	 */
	protected Authentication authenticate() {
		return authenticate(null, null, null)
	}

	/**
	 * Register a currently authenticated user.
	 *
	 * @param principal  the principal
	 * @param credentials  the password
	 * @param authorities  the roles
	 * @return  the authentication
	 */
	protected Authentication authenticate(Object principal, Object credentials, GrantedAuthority[] authorities) {
		Authentication authentication = new TestingAuthenticationToken(principal, credentials, authorities)
		authentication.authenticated = true
		SCH.context.authentication = authentication
		return authentication
	}

	/**
	 * Remove overridden/added metaclass methods between tests.
	 * @param classes  the classes to clean up
	 */
	protected void removeMetaClassMethods(final Class<?>... classes) {
		classes.each { clazz ->
			def emc = new ExpandoMetaClass(clazz, true, true)
			emc.initialize()
			GroovySystem.metaClassRegistry.setMetaClass(clazz, emc)
		}
	}
}
