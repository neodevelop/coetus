import org.codehaus.groovy.grails.commons.ControllerArtefactHandler

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsAccessDeniedHandlerImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsAuthenticationProcessingFilter
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsDaoImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsFilterInvocationDefinition
import org.codehaus.groovy.grails.plugins.springsecurity.LogoutFilterFactoryBean
import org.codehaus.groovy.grails.plugins.springsecurity.QuietMethodSecurityInterceptor
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityAnnotationAttributes
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityEventListener
import org.codehaus.groovy.grails.plugins.springsecurity.WithAjaxAuthenticationProcessingFilterEntryPoint
import org.codehaus.groovy.grails.plugins.springsecurity.ldap.GrailsLdapUserDetailsMapper

import org.openid4java.consumer.ConsumerManager
import org.openid4java.consumer.InMemoryConsumerAssociationStore
import org.openid4java.consumer.InMemoryNonceVerifier
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.beans.factory.config.RuntimeBeanReference
import org.springframework.cache.ehcache.EhCacheFactoryBean
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.security.annotation.Secured
import org.springframework.security.context.HttpSessionContextIntegrationFilter
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.event.authentication.LoggerListener
import org.springframework.security.intercept.method.MethodDefinitionAttributes
import org.springframework.security.intercept.web.FilterSecurityInterceptor
import org.springframework.security.ldap.DefaultSpringSecurityContextSource
import org.springframework.security.ldap.populator.DefaultLdapAuthoritiesPopulator
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch
import org.springframework.security.providers.openid.OpenIDAuthenticationProvider
import org.springframework.security.ui.ExceptionTranslationFilter
import org.springframework.security.ui.basicauth.BasicProcessingFilter
import org.springframework.security.ui.basicauth.BasicProcessingFilterEntryPoint
import org.springframework.security.ui.logout.LogoutHandler
import org.springframework.security.ui.logout.SecurityContextLogoutHandler
import org.springframework.security.ui.openid.OpenIDAuthenticationProcessingFilter
import org.springframework.security.ui.openid.consumers.OpenID4JavaConsumer
import org.springframework.security.ui.rememberme.RememberMeProcessingFilter
import org.springframework.security.ui.rememberme.TokenBasedRememberMeServices
import org.springframework.security.ui.switchuser.SwitchUserProcessingFilter
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter
import org.springframework.security.providers.ProviderManager
import org.springframework.security.providers.anonymous.AnonymousAuthenticationProvider
import org.springframework.security.providers.anonymous.AnonymousProcessingFilter
import org.springframework.security.providers.dao.DaoAuthenticationProvider
import org.springframework.security.providers.dao.cache.EhCacheBasedUserCache
import org.springframework.security.providers.encoding.MessageDigestPasswordEncoder
import org.springframework.security.providers.ldap.LdapAuthenticationProvider
import org.springframework.security.providers.ldap.authenticator.BindAuthenticator
import org.springframework.security.providers.rememberme.RememberMeAuthenticationProvider
import org.springframework.security.util.FilterChainProxy
import org.springframework.security.vote.AffirmativeBased
import org.springframework.security.vote.AuthenticatedVoter
import org.springframework.security.vote.RoleVoter
import org.springframework.security.wrapper.SecurityContextHolderAwareRequestFilter
import org.springframework.web.filter.DelegatingFilterProxy

/**
 * Grails Spring Security 2.0 Plugin.
 *
 * @author T.Yamamoto
 * @author Haotian Sun
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
class AcegiGrailsPlugin {

	def version = '0.3'
	def author = 'Tsuyoshi Yamamoto'
	def authorEmail = 'tyama@xmldo.jp'
	def title = 'Grails Spring Security 2.0 Plugin'
	def description = 'Plugin to use Grails domain class and secure your applications with Spring Security filters.'
	def documentation ="http://grails.org/AcegiSecurity+Plugin"
	def observe = ['controllers']
	def loadAfter = ['controllers']
	def watchedResources = [
		'file:./grails-app/controllers/**/*Controller.groovy',
		'file:./plugins/*/grails-app/controllers/**/*Controller.groovy'
	]

	def dependsOn = [:]

	def doWithSpring = {

		def conf = getSecurityConfig()
		if (!conf || !conf.active) {
			//log.info('[active=false] Spring Security will not loaded')
			println '[active=false] Spring Security will not loaded'
			return
		}

		//log.info('loading security config ...')
		println 'loading security config ...'

		def makeItGetter = { field ->
			if (!field) {
				return null
			}
			'get' + field[0].toUpperCase() + field.substring(1)	
		}

		def filterNames = conf.filterNames
		if (!filterNames) {
			filterNames = []
			filterNames << 'httpSessionContextIntegrationFilter'
			filterNames << 'logoutFilter'
			filterNames << 'authenticationProcessingFilter'
			if (conf.useOpenId) {
				filterNames << 'openIDAuthenticationProcessingFilter'
			}
			if (conf.basicProcessingFilter) {
				filterNames << 'basicProcessingFilter'
			}
			filterNames << 'securityContextHolderAwareRequestFilter'
			filterNames << 'rememberMeProcessingFilter'
			filterNames << 'anonymousProcessingFilter'
			filterNames << 'exceptionTranslationFilter'
			filterNames << 'filterInvocationInterceptor'
			if (conf.switchUserProcessingFilter) {
				filterNames << 'switchUserProcessingFilter'
			}
		}

		/** springSecurityFilterChain */
		springSecurityFilterChain(FilterChainProxy) {
			filterInvocationDefinitionSource = """
				CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
				PATTERN_TYPE_APACHE_ANT
				/**=${filterNames.join(',')}
				"""
		}

		/** OpenId */
		openIDAuthProvider(OpenIDAuthenticationProvider) {
			userDetailsService = ref('userDetailsService')
		}
		openIDStore(InMemoryConsumerAssociationStore) {}
		openIDNonceVerifier(InMemoryNonceVerifier, conf.openIdNonceMaxSeconds) {} // 300 seconds
		openIDConsumerManager(ConsumerManager) {
			nonceVerifier = ref('openIDNonceVerifier')
		}
		openIDConsumer(OpenID4JavaConsumer, openIDConsumerManager) {}
		openIDAuthenticationProcessingFilter(OpenIDAuthenticationProcessingFilter) {
			authenticationManager = ref('authenticationManager')
			authenticationFailureUrl = conf.authenticationFailureUrl //'/login/authfail?login_error=1' // /spring_security_login?login_error
			defaultTargetUrl = conf.defaultTargetUrl // '/'
			filterProcessesUrl = '/j_spring_openid_security_check' // not configurable
			rememberMeServices = ref('rememberMeServices')
			consumer = ref('openIDConsumer')
		}

		/** httpSessionContextIntegrationFilter */
		httpSessionContextIntegrationFilter(HttpSessionContextIntegrationFilter) {}

		securityContextLogoutHandler(SecurityContextLogoutHandler) {}
		def logoutHandlerNames = conf.logoutHandlerNames
		if (!logoutHandlerNames) {
			logoutHandlerNames = ['rememberMeServices', 'securityContextLogoutHandler']
		}

		def logoutHandlers = createRefList(logoutHandlerNames)
		def afterLogoutUrl = conf.afterLogoutUrl // '/'

		/** logoutFilter */
		logoutFilter(LogoutFilterFactoryBean) {
			logoutSuccessUrl = afterLogoutUrl
			handlers = logoutHandlers
		}

		/** authenticationProcessingFilter */
		authenticationProcessingFilter(GrailsAuthenticationProcessingFilter) {
			authenticationManager = ref('authenticationManager')
			authenticationFailureUrl = conf.authenticationFailureUrl //'/login/authfail?login_error=1'
			ajaxAuthenticationFailureUrl = conf.ajaxAuthenticationFailureUrl // /login/authfail?ajax=true
			defaultTargetUrl = conf.defaultTargetUrl // '/'
			filterProcessesUrl = conf.filterProcessesUrl // '/j_spring_security_check'
			rememberMeServices = ref('rememberMeServices')
			authenticateService = ref('authenticateService')
		}

		// Basic Auth
		basicProcessingFilter(BasicProcessingFilter) {
			authenticationManager = ref('authenticationManager')
			authenticationEntryPoint = ref('basicProcessingFilterEntryPoint')
		}
		basicProcessingFilterEntryPoint(BasicProcessingFilterEntryPoint) {
			realmName = conf.realmName // 'Grails Realm'
		}

		/** securityContextHolderAwareRequestFilter */
		securityContextHolderAwareRequestFilter(SecurityContextHolderAwareRequestFilter) {}

		/** rememberMeProcessingFilter */
		rememberMeProcessingFilter(RememberMeProcessingFilter) {
			authenticationManager = ref('authenticationManager')
			rememberMeServices = ref('rememberMeServices')
		}
		/** rememberMeServices */
		rememberMeServices(TokenBasedRememberMeServices) {
			userDetailsService = ref('userDetailsService')
			key = conf.rememberMeKey
			cookieName = conf.cookieName
			alwaysRemember = conf.alwaysRemember
			tokenValiditySeconds = conf.tokenValiditySeconds
			parameter = conf.parameter
		}

		/** anonymousProcessingFilter */
		anonymousProcessingFilter(AnonymousProcessingFilter) {
			key = conf.key // 'foo'
			userAttribute = conf.userAttribute //'anonymousUser,ROLE_ANONYMOUS'
		}

		/** exceptionTranslationFilter */
		exceptionTranslationFilter(ExceptionTranslationFilter) {
			authenticationEntryPoint = ref('authenticationEntryPoint')
			accessDeniedHandler = ref('accessDeniedHandler')
		}
		accessDeniedHandler(GrailsAccessDeniedHandlerImpl) {
			errorPage = conf.errorPage == 'null' ? null : conf.errorPage // '/login/denied' or 403
			ajaxErrorPage = conf.ajaxErrorPage
			if (conf.ajaxHeader) {
				ajaxHeader = conf.ajaxHeader //default: X-Requested-With
			}
		}

		authenticationEntryPoint(WithAjaxAuthenticationProcessingFilterEntryPoint) {
			loginFormUrl = conf.loginFormUrl // '/login/auth'
			forceHttps = conf.forceHttps // 'false'
			ajaxLoginFormUrl = conf.ajaxLoginFormUrl // '/login/authAjax'
			if (conf.ajaxHeader) {
				ajaxHeader = conf.ajaxHeader //default: X-Requested-With
			}
		}

		roleVoter(RoleVoter) {}
		authenticatedVoter(AuthenticatedVoter) {}
		def decisionVoterNames = conf.decisionVoterNames
		if (!decisionVoterNames) {
			decisionVoterNames = ['roleVoter', 'authenticatedVoter']
		}
		def decisionVoterList = createRefList(decisionVoterNames)
		/** accessDecisionManager */
		accessDecisionManager(AffirmativeBased) {
			allowIfAllAbstainDecisions = false
			decisionVoters = decisionVoterList
		}

		/** filterInvocationInterceptor */
		filterInvocationInterceptor(FilterSecurityInterceptor) {
			authenticationManager = ref('authenticationManager')
			accessDecisionManager = ref('accessDecisionManager')
			if (conf.useRequestMapDomainClass) {
				objectDefinitionSource = ref('objectDefinitionSource')
			}
			else {
				objectDefinitionSource = conf.requestMapString
			}
		}
		if (conf.useRequestMapDomainClass) {
			objectDefinitionSource(GrailsFilterInvocationDefinition) {
				requestMapClass = conf.requestMapClass
				requestMapPathFieldName = conf.requestMapPathField
				requestMapConfigAttributeField = conf.requestMapConfigAttributeField
			}
		}

		/** anonymousAuthenticationProvider */
		anonymousAuthenticationProvider(AnonymousAuthenticationProvider) {
			key = conf.key // "foo"
		}
		/** rememberMeAuthenticationProvider */
		rememberMeAuthenticationProvider(RememberMeAuthenticationProvider) {
			key = conf.rememberMeKey
		}

		def providerNames = conf.providerNames
		if (!providerNames) {
			if (conf.useLdap) {
				providerNames = ['ldapAuthProvider']
			}
			else {
				providerNames = ['daoAuthenticationProvider']
				if (conf.useOpenId) {
					providerNames << 'openIDAuthProvider'
				}
			}
			providerNames << 'anonymousAuthenticationProvider'
			providerNames << 'rememberMeAuthenticationProvider'
		}

		def providerList = createRefList(providerNames)
		/** authenticationManager */
		authenticationManager(ProviderManager) {
			providers = providerList
		}

		/** daoAuthenticationProvider */
		daoAuthenticationProvider(DaoAuthenticationProvider) {
			userDetailsService = ref('userDetailsService')
			passwordEncoder = ref('passwordEncoder')
			userCache = ref('userCache')
		}

		/** passwordEncoder */
		passwordEncoder(MessageDigestPasswordEncoder, conf.algorithm) {
			if (conf.encodeHashAsBase64) {
				encodeHashAsBase64 = true
			}
		}

		userCache(EhCacheBasedUserCache) {
			cache = ref('securityUserCache')
		}
		securityUserCache(EhCacheFactoryBean) {
			cacheManager = ref('cacheManager')
			cacheName = 'userCache'
		}
		cacheManager(EhCacheManagerFactoryBean) {}

		/** userDetailsService */
		userDetailsService(GrailsDaoImpl) {
			usernameFieldName = conf.userName
			passwordFieldName = conf.password
			enabledFieldName = conf.enabled
			authorityFieldName = conf.authorityField
			loginUserDomainClass = conf.loginUserDomainClass
			relationalAuthoritiesField = conf.relationalAuthorities
			authoritiesMethodName = conf.getAuthoritiesMethod
		}

		/** loggerListener ( log4j.logger.org.springframework.security=info,stdout ) */
		if (conf.useLogger) {
			loggerListener(LoggerListener) {}
		}

		daacc(DefaultAdvisorAutoProxyCreator) {}

		// experiment on Annotation and MethodSecurityInterceptor for secure services
		serviceSecureAnnotation(SecurityAnnotationAttributes) {}
		serviceSecureAnnotationODS(MethodDefinitionAttributes) {
			attributes = ref('serviceSecureAnnotation')
		}
		/** securityInteceptor */
		securityInteceptor(QuietMethodSecurityInterceptor) {
			validateConfigAttributes = false
			authenticationManager = ref('authenticationManager')
			accessDecisionManager = ref('accessDecisionManager')
			objectDefinitionSource = ref('serviceSecureAnnotationODS')
			throwException = true
		}

		//load Services which have Annotations
		application.serviceClasses.each { serviceClass ->
			if (hasAnnotation(serviceClass.clazz)) {
				"${serviceClass.propertyName}Sec"(BeanNameAutoProxyCreator) {
					beanNames = "${serviceClass.propertyName}"
					interceptorNames = ['securityInteceptor']
					proxyTargetClass = true
				}
			}
		}

		//load simple java mail settings
		def useMail = conf.useMail
		if (useMail) {
			mailSender(JavaMailSenderImpl) {
				host = conf.mailHost
				username = conf.mailUsername
				password = conf.mailPassword
				protocol = conf.mailProtocol
				port = conf.mailPort
				if (conf.javaMailProperties) {
					javaMailProperties = conf.javaMailProperties as Properties
				}
			}

			mailMessage(SimpleMailMessage) {
				from = conf.mailFrom
			}
		}

		//Switch User
		if (conf.switchUserProcessingFilter) {
			switchUserProcessingFilter(SwitchUserProcessingFilter) {
				userDetailsService = ref('userDetailsService')
				switchUserUrl = conf.swswitchUserUrl
				exitUserUrl = conf.swexitUserUrl
				targetUrl = conf.swtargetUrl
			}
		}

		// LDAP
		if (conf.useLdap) {
			contextSource(DefaultSpringSecurityContextSource, conf.ldapServer) {
				userDn = conf.ldapManagerDn
				password = conf.ldapManagerPassword
			}

			ldapUserSearch(FilterBasedLdapUserSearch, conf.ldapSearchBase, conf.ldapSearchFilter, ref('contextSource')) {
				searchSubtree = conf.ldapSearchSubtree
			}

			ldapAuthenticator(BindAuthenticator, ref('contextSource')) {
				userSearch = ref('ldapUserSearch')
			}
			ldapUserDetailsMapper(GrailsLdapUserDetailsMapper) {
				grailsDaoImpl = ref('userDetailsService')
				authenticateService = ref('authenticateService')
				passwordAttributeName = conf.ldapPasswordAttributeName // 'userPassword'
			}
			if (conf.ldapRetrieveGroupRoles) {
				ldapAuthoritiesPopulator(DefaultLdapAuthoritiesPopulator, ref('contextSource'), conf.ldapGroupSearchBase) {
					groupRoleAttribute = conf.ldapGroupRoleAttribute
					groupSearchFilter = conf.ldapGroupSearchFilter
				}
				ldapAuthProvider(LdapAuthenticationProvider, ref('ldapAuthenticator'), ref('ldapAuthoritiesPopulator')) {
					userDetailsContextMapper = ref('ldapUserDetailsMapper')
				}
			}
			else {
				// use the NullAuthoritiesPopulator
				ldapAuthProvider(LdapAuthenticationProvider, ref('ldapAuthenticator')) {
					userDetailsContextMapper = ref('ldapUserDetailsMapper')
				}
			}
		}

		// SecurityEventListener
		securityEventListener(SecurityEventListener) {
			authenticateService = ref('authenticateService')
		}
	}

	def doWithApplicationContext = { applicationContext ->
		// nothing to do		
	}

	def doWithWebDescriptor = { xml ->

		def conf = getSecurityConfig()
		if (conf && conf.active) {
			def contextParam = xml.'context-param'
			contextParam[contextParam.size() - 1] + {
				'filter' {
					'filter-name'('springSecurityFilterChain')
					'filter-class'(DelegatingFilterProxy.name)
				}
			}

			def filter = xml.'filter'
			filter[filter.size() - 1] + {
				'filter-mapping'{
					'filter-name'('springSecurityFilterChain')
					'url-pattern'('/*')
				}
			}
		}
	}

	def doWithDynamicMethods = { ctx ->
		for (controller in application.controllerClasses) {
			registerControllerProps(controller.metaClass)
		}
	}

	def onChange = { event ->
		if (application.isArtefactOfType(ControllerArtefactHandler.TYPE, event.source)) {
			def controllerClass = application.addArtefact(ControllerArtefactHandler.TYPE, event.source)
			registerControllerProps(controllerClass.metaClass)
		}
	}

	def onApplicationChange = { event ->
		// nothing to do
	}

	private void registerControllerProps(MetaClass mc) {
		mc.getAuthUserDomain = { ->
			def principal = SCH.context?.authentication?.principal
			if (principal != null && principal != 'anonymousUser') {
				return principal?.domainClass
			}

			return null
		}

		mc.getPrincipalInfo = { ->
			return SCH.context?.authentication?.principal
		}

		mc.isUserLogon = { ->
			def principal = SCH.context?.authentication?.principal
			return principal != null && principal != 'anonymousUser'
		}
	}

	private ConfigObject getSecurityConfig() {

		GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader())

		ConfigObject userConfig
		try {
			userConfig = new ConfigSlurper().parse(classLoader.loadClass('SecurityConfig'))
		}
		catch (Exception ignored) {
			// ignored, use defaults
		}

		ConfigObject config
		ConfigObject defaultConfig = new ConfigSlurper().parse(classLoader.loadClass('DefaultSecurityConfig'))
		if (userConfig) {
			//log.info('using user SecurityConfig')
			config = defaultConfig.merge(userConfig)
		}
		else {
			//log.info('using DefaultSecurityConfig')
			config = defaultConfig
		}

		return config.security
	}

	private boolean hasAnnotation(serviceClass) {
		for (method in serviceClass.methods) {
			for (annotation in method.annotations) {
				if (annotation instanceof Secured) {
					return true
				}
			}
		}

		return false
	}

	private createRefList(names) {
		// 'ref' method isn't available in a method, so call what it calls
		names.collect { name -> new RuntimeBeanReference(name, false) }
	}
}
