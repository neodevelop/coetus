package org.codehaus.groovy.grails.plugins.springsecurity

import org.aopalliance.intercept.MethodInvocation 
import org.easymock.EasyMock
import org.springframework.security.AccessDeniedException
import org.springframework.security.AfterInvocationManager
import org.springframework.security.Authentication
import org.springframework.security.AccessDecisionManager
import org.springframework.security.ConfigAttributeDefinition
import org.springframework.security.intercept.method.MethodDefinitionSource

/**
 * Unit tests for QuietMethodSecurityInterceptor.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class QuietMethodSecurityInterceptorTests extends AbstractSecurityTest {

	private final QuietMethodSecurityInterceptor _interceptor = new QuietMethodSecurityInterceptor()

	private Authentication _authentication

	/**
	 * Test invoke() where beforeInvocation() throws an exception and quiet is true.
	 */
	void testInvokeBeforeThrowsQuiet() {

		_interceptor.throwException = false

		MethodInvocation mi = EasyMock.createMock(MethodInvocation)
		MethodDefinitionSource mds = EasyMock.createMock(MethodDefinitionSource)
		_interceptor.objectDefinitionSource = mds

		def attributes = new ConfigAttributeDefinition('foo')
		EasyMock.expect(mds.getAttributes(mi)).andReturn(attributes)

		// wire up the failure exception
		AccessDecisionManager accessDecisionManager = EasyMock.createMock(AccessDecisionManager)
		_interceptor.accessDecisionManager = accessDecisionManager
		accessDecisionManager.decide(_authentication, mi, attributes)
		Exception exception = new AccessDeniedException('denied')
		EasyMock.expectLastCall().andThrow(exception)

		EasyMock.replay(mi, mds, accessDecisionManager)

		def result = _interceptor.invoke(mi)
		assertNull result
		assertSame exception, _interceptor.lastException

		EasyMock.verify(mi, mds, accessDecisionManager)
	}

	/**
	 * Test invoke() where beforeInvocation() throws an exception and quiet is false.
	 */
	void testInvokeBeforeThrowsNotQuiet() {

		_interceptor.throwException = true

		MethodInvocation mi = EasyMock.createMock(MethodInvocation)
		MethodDefinitionSource mds = EasyMock.createMock(MethodDefinitionSource)
		_interceptor.objectDefinitionSource = mds

		def attributes = new ConfigAttributeDefinition('foo')
		EasyMock.expect(mds.getAttributes(mi)).andReturn(attributes)

		// wire up the failure exception
		AccessDecisionManager accessDecisionManager = EasyMock.createMock(AccessDecisionManager)
		_interceptor.accessDecisionManager = accessDecisionManager
		accessDecisionManager.decide(_authentication, mi, attributes)
		Exception exception = new AccessDeniedException('denied')
		EasyMock.expectLastCall().andThrow(exception)

		EasyMock.replay(mi, mds, accessDecisionManager)

		shouldFail(AccessDeniedException) {
			_interceptor.invoke(mi)
		}

		EasyMock.verify(mi, mds, accessDecisionManager)
	}

	/**
	 * Test invoke() where invoke throws an exception and quiet is true.
	 */
	void testInvokeThrowsQuiet() {

		_interceptor.throwException = false

		MethodInvocation mi = EasyMock.createMock(MethodInvocation)
		MethodDefinitionSource mds = EasyMock.createMock(MethodDefinitionSource)
		_interceptor.objectDefinitionSource = mds

		def attributes = new ConfigAttributeDefinition('foo')
		EasyMock.expect(mds.getAttributes(mi)).andReturn(attributes)

		// let beforeInvocation proceed
		AccessDecisionManager accessDecisionManager = EasyMock.createMock(AccessDecisionManager)
		_interceptor.accessDecisionManager = accessDecisionManager
		accessDecisionManager.decide(_authentication, mi, attributes)
		EasyMock.expectLastCall()

		// wire up the failure exception
		Exception exception = new FooException('foo')
		EasyMock.expect(mi.proceed()).andThrow(exception)

		EasyMock.replay(mi, mds, accessDecisionManager)

		def result = _interceptor.invoke(mi)
		assertNull result
		assertSame exception, _interceptor.lastException

		EasyMock.verify(mi, mds, accessDecisionManager)
	}

	/**
	 * Test invoke() where invoke throws an exception and quiet is false.
	 */
	void testInvokeThrowsNotQuiet() {

		_interceptor.throwException = true

		MethodInvocation mi = EasyMock.createMock(MethodInvocation)
		MethodDefinitionSource mds = EasyMock.createMock(MethodDefinitionSource)
		_interceptor.objectDefinitionSource = mds

		def attributes = new ConfigAttributeDefinition('foo')
		EasyMock.expect(mds.getAttributes(mi)).andReturn(attributes)

		// let beforeInvocation proceed
		AccessDecisionManager accessDecisionManager = EasyMock.createMock(AccessDecisionManager)
		_interceptor.accessDecisionManager = accessDecisionManager
		accessDecisionManager.decide(_authentication, mi, attributes)
		EasyMock.expectLastCall()

		// wire up the failure exception
		Exception exception = new FooException('foo')
		EasyMock.expect(mi.proceed()).andThrow(exception)

		EasyMock.replay(mi, mds, accessDecisionManager)

		shouldFail(FooException) {
			_interceptor.invoke(mi)
		}

		EasyMock.verify(mi, mds, accessDecisionManager)
	}

	/**
	 * Test invoke() where afterInvocation throws an exception and quiet is true.
	 */
	void testInvokeAfterThrowsQuiet() {

		_interceptor.throwException = false

		MethodInvocation mi = EasyMock.createMock(MethodInvocation)
		MethodDefinitionSource mds = EasyMock.createMock(MethodDefinitionSource)
		_interceptor.objectDefinitionSource = mds

		def attributes = new ConfigAttributeDefinition('foo')
		EasyMock.expect(mds.getAttributes(mi)).andReturn(attributes)

		AfterInvocationManager afterInvocationManager = EasyMock.createMock(AfterInvocationManager)
		_interceptor.afterInvocationManager = afterInvocationManager;

		// let beforeInvocation proceed
		AccessDecisionManager accessDecisionManager = EasyMock.createMock(AccessDecisionManager)
		_interceptor.accessDecisionManager = accessDecisionManager
		accessDecisionManager.decide(_authentication, mi, attributes)
		EasyMock.expectLastCall()

		// successful proceed()
		def retval = 'success!'
		EasyMock.expect(mi.proceed()).andReturn(retval)

		// wire up the failure exception
		Exception exception = new RuntimeException('foo')
		EasyMock.expect(afterInvocationManager.decide(_authentication, mi, attributes, retval)).andThrow(exception)

		EasyMock.replay(mi, mds, accessDecisionManager, afterInvocationManager)

		def result = _interceptor.invoke(mi)
		assertNull result
		assertSame exception, _interceptor.lastException

		EasyMock.verify(mi, mds, accessDecisionManager, afterInvocationManager)
	}

	/**
	 * Test invoke() where afterInvocation throws an exception and quiet is false.
	 */
	void testInvokeAfterThrowsNotQuiet() {

		_interceptor.throwException = true

		MethodInvocation mi = EasyMock.createMock(MethodInvocation)
		MethodDefinitionSource mds = EasyMock.createMock(MethodDefinitionSource)
		_interceptor.objectDefinitionSource = mds

		def attributes = new ConfigAttributeDefinition('foo')
		EasyMock.expect(mds.getAttributes(mi)).andReturn(attributes)

		AfterInvocationManager afterInvocationManager = EasyMock.createMock(AfterInvocationManager)
		_interceptor.afterInvocationManager = afterInvocationManager;

		// let beforeInvocation proceed
		AccessDecisionManager accessDecisionManager = EasyMock.createMock(AccessDecisionManager)
		_interceptor.accessDecisionManager = accessDecisionManager
		accessDecisionManager.decide(_authentication, mi, attributes)
		EasyMock.expectLastCall()

		// successful proceed()
		def retval = 'success!'
		EasyMock.expect(mi.proceed()).andReturn(retval)

		// wire up the failure exception
		Exception exception = new FooException('foo')
		EasyMock.expect(afterInvocationManager.decide(_authentication, mi, attributes, retval)).andThrow(exception)

		EasyMock.replay(mi, mds, accessDecisionManager, afterInvocationManager)

		shouldFail(FooException) {
			_interceptor.invoke(mi)
		}

		EasyMock.verify(mi, mds, accessDecisionManager, afterInvocationManager)
	}

	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() {
		super.setUp()
		_authentication = authenticate()
	}
}

class FooException extends RuntimeException {
	FooException(String message) {
		super(message)
	}
}
