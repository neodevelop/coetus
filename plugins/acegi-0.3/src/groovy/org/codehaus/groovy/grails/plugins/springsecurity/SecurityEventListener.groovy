package org.codehaus.groovy.grails.plugins.springsecurity

import org.grails.plugins.springsecurity.service.AuthenticateService

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.security.event.authentication.AbstractAuthenticationEvent
import org.springframework.security.event.authentication.AbstractAuthenticationFailureEvent
import org.springframework.security.event.authentication.AuthenticationSuccessEvent
import org.springframework.security.event.authentication.AuthenticationSwitchUserEvent
import org.springframework.security.event.authentication.InteractiveAuthenticationSuccessEvent
import org.springframework.security.event.authorization.AbstractAuthorizationEvent

/**
 * Registers as an event listener and delegates handling of security-related events
 * to optional closures defined in SecurityConfig.groovy.
 * <p/>
 * The following callbacks are supported:<br/>
 * <ul>
 * <li>onInteractiveAuthenticationSuccessEvent</li>
 * <li>onAbstractAuthenticationFailureEvent</li>
 * <li>onAuthenticationSuccessEvent</li>
 * <li>onAuthenticationSwitchUserEvent</li>
 * <li>onAuthorizationEvent</li>
 * </ul>
 * All callbacks are optional; you can implement just the ones you're interested in, e.g.
 * <pre>
 * security {
 *    active = true
 *
 *    onAuthenticationSuccessEvent = { e, appCtx ->
 *       ...
 *    }
 * }
 * </pre>
 * The event and the Spring context are provided in case you need to look up a Spring bean,
 * e.g. the Hibernate SessionFactory.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class SecurityEventListener implements ApplicationListener, ApplicationContextAware {

	private ApplicationContext _applicationContext;

	/**
	 * Dependency injection for AuthenticateService.
	 */
	AuthenticateService authenticateService

	/**
	 * {@inheritDoc}
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(
	 * 	org.springframework.context.ApplicationEvent)
	 */
	void onApplicationEvent(final ApplicationEvent e) {
		if (e instanceof AbstractAuthenticationEvent) {
			if (e instanceof InteractiveAuthenticationSuccessEvent) {
				if (config.onInteractiveAuthenticationSuccessEvent) {
					config.onInteractiveAuthenticationSuccessEvent.call(
							(InteractiveAuthenticationSuccessEvent)e, _applicationContext)
				}
			}
			else if (e instanceof AbstractAuthenticationFailureEvent) {
				if (config.onAbstractAuthenticationFailureEvent) {
					config.onAbstractAuthenticationFailureEvent.call(
							(AbstractAuthenticationFailureEvent)e, _applicationContext)
				}
			}
			else if (e instanceof AuthenticationSuccessEvent) {
				if (config.onAuthenticationSuccessEvent) {
					config.onAuthenticationSuccessEvent.call(
							(AuthenticationSuccessEvent)e, _applicationContext)
				}
			}
			else if (e instanceof AuthenticationSwitchUserEvent) {
				if (config.onAuthenticationSwitchUserEvent) {
//					GrailsUser userInfo = (GrailsUser)event.getAuthentication().getPrincipal()
//					UserDetails userDetails = event.getTargetUser()
					config.onAuthenticationSwitchUserEvent.call(
							(AuthenticationSwitchUserEvent)e, _applicationContext)
				}
			}
		}
		else if (e instanceof AbstractAuthorizationEvent) {
			if (config.onAuthorizationEvent) {
				config.onAuthorizationEvent.call(
						(AbstractAuthorizationEvent)e, _applicationContext)
			}
		}
	}

 	/**
 	 * {@inheritDoc}
 	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(
 	 * 	org.springframework.context.ApplicationContext)
 	 */
 	void setApplicationContext(final ApplicationContext applicationContext) {
 		_applicationContext = applicationContext;		
 	}

	private def getConfig() {
		return authenticateService.securityConfig.security
	}
}
