package org.codehaus.groovy.grails.plugins.springsecurity

import org.easymock.EasyMock
import org.hibernate.Query
import org.hibernate.Session
import org.springframework.security.GrantedAuthorityImpl
import org.springframework.security.userdetails.UserDetails
import org.springframework.security.userdetails.UsernameNotFoundException

/**
 * Integration tests for <code>GrailsDaoImpl</code>.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class GrailsDaoImplTests extends AbstractSecurityTest {

	private final GrailsDaoImpl _dao = new GrailsDaoImpl()

	/**
	 * Test loadUserByUsername() when user not found.
	 */
	void testLoadUserByUsernameNotFound() {

		String username = 'not_a_user'

		Session session = EasyMock.createMock(Session)
		Query query = EasyMock.createMock(Query)
		EasyMock.expect(session.createQuery((String)EasyMock.anyObject())).andReturn(query)
		EasyMock.expect(query.list()).andReturn([])
		EasyMock.expect(query.setProperties([username: username])).andReturn(query)

		EasyMock.replay(session, query)

		GrailsWebApplicationObjectSupport.SessionContainer container =
 			new GrailsWebApplicationObjectSupport.SessionContainer(session, true)

		// replace definition to avoid going to the database
		_dao.metaClass.setUpSession = { -> container }

		shouldFail(UsernameNotFoundException) {
			_dao.loadUserByUsername(username)
		}

		EasyMock.verify(session, query)
	}

	/**
	 * Test loadUserByUsername() when user has no roles.
	 */
	void testLoadUserByUsernameNoRoles() {

		String username = 'a_user'
		def user = new Expando()

		Session session = EasyMock.createMock(Session)
		Query query = EasyMock.createMock(Query)
		EasyMock.expect(session.createQuery((String)EasyMock.anyObject())).andReturn(query)
		EasyMock.expect(query.setProperties([username: username])).andReturn(query)
		EasyMock.expect(query.list()).andReturn([user])

		EasyMock.replay(session, query)

		GrailsWebApplicationObjectSupport.SessionContainer container =
 			new GrailsWebApplicationObjectSupport.SessionContainer(session, true)

		// replace definition to avoid going to the database
		_dao.metaClass.setUpSession = { -> container }

		_dao.authoritiesMethodName = 'getRoles'
		user.getRoles = { -> [] }

		def message = shouldFail(UsernameNotFoundException) {
			_dao.loadUserByUsername(username)
		}
		assertEquals 'User has no GrantedAuthority', message

		EasyMock.verify(session, query)
	}

	/**
	 * Test loadUserByUsername().
	 */
	void testLoadUserByUsername() {

		String username = 'a_user'
		def user = new Expando()

		Session session = EasyMock.createMock(Session)
		Query query = EasyMock.createMock(Query)
		EasyMock.expect(session.createQuery((String)EasyMock.anyObject())).andReturn(query)
		EasyMock.expect(query.setProperties([username: username])).andReturn(query)
		EasyMock.expect(query.list()).andReturn([user])

		EasyMock.replay(session, query)

		GrailsWebApplicationObjectSupport.SessionContainer container =
 			new GrailsWebApplicationObjectSupport.SessionContainer(session, true)

		// replace definition to avoid going to the database
		_dao.metaClass.setUpSession = { -> container }

		_dao.authoritiesMethodName = 'getRoles'
		user.getRoles = { -> ['role1'] }
		_dao.passwordFieldName = 'password'
		user.password = 'passw0rd'
		_dao.enabledFieldName = 'enabled'
		user.enabled = true 

		UserDetails details = _dao.loadUserByUsername(username)
		assertNotNull details

		EasyMock.verify(session, query)
	}

	/**
	 * Test createRolesByAuthoritiesMethod().
	 */
	void testCreateRolesByAuthoritiesMethod() {
		_dao.authoritiesMethodName = 'getRoles'
		def user = new Expando()
		user.getRoles = { -> ['role1', 'role2'] }

		def authorities = _dao.createRolesByAuthoritiesMethod(user, 'foo')
		assertEquals 2, authorities.size()
		assertEquals 'role1', authorities[0].authority
		assertEquals 'role2', authorities[1].authority
	}

	/**
	 * Test createRolesByRelationalAuthorities().
	 */
	void testCreateRolesByRelationalAuthorities() {
		_dao.relationalAuthoritiesField = 'roles'
		_dao.authorityFieldName = 'auth'

		def user = new Expando()
		user.roles = [[auth: 'role1'], [auth: 'role2']]

		def authorities = _dao.createRolesByRelationalAuthorities(user, 'foo')
		assertEquals 2, authorities.size()
		assertEquals 'role1', authorities[0].authority
		assertEquals 'role2', authorities[1].authority
	}

	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() {
		super.tearDown()
		removeMetaClassMethods(GrailsDaoImpl)
	}
}
