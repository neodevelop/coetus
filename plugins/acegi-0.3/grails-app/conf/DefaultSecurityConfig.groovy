security {

	/** enable Spring Security or not */
	active = false

	/** login user class fields (default user class = Person)*/
	loginUserDomainClass = 'Person'
	userName = 'username'
	password = 'passwd'
	enabled = 'enabled'
	relationalAuthorities = 'authorities'
	//you can specify method for to retrieve the roles. (you need to set relationalAuthorities=null)
	getAuthoritiesMethod = null // 'getMoreAuthorities'

	/**
	 * Authority domain class authority field name 
	 * authorityFieldInList
	 */
	authorityDomainClass = 'Authority'
	authorityField = 'authority'

	/** authenticationProcessingFilter */
	authenticationFailureUrl = '/login/authfail?login_error=1'
	ajaxAuthenticationFailureUrl = '/login/authfail?ajax=true'
	defaultTargetUrl = '/'
	filterProcessesUrl = '/j_spring_security_check'

	/** anonymousProcessingFilter */
	key = 'foo'
	userAttribute = 'anonymousUser,ROLE_ANONYMOUS'

	/** authenticationEntryPoint */
	loginFormUrl = '/login/auth'
	forceHttps = 'false'
	ajaxLoginFormUrl = '/login/authAjax'

	/** logoutFilter */
	afterLogoutUrl = '/'

	/** accessDeniedHandler 
	 *  set errorPage to null, if you want to get error code 403 (FORBIDDEN).
	 */
	errorPage = '/login/denied'
	ajaxErrorPage = '/login/deniedAjax'
	ajaxHeader = 'X-Requested-With'

	/** passwordEncoder */
	//The digest algorithm to use.
	//Supports the named Message Digest Algorithms in the Java environment.
	//http://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html#AppA
	algorithm = 'SHA' // Ex. MD5 SHA 
	//use Base64 text ( true or false )
	encodeHashAsBase64 = false

	/** rememberMeServices */
	cookieName = 'grails_remember_me' 
	alwaysRemember = false
	tokenValiditySeconds = 1209600 //14 days
	parameter = '_spring_security_remember_me'
	rememberMeKey = 'grailsRocks'

	/** LoggerListener 
	 * ( add 'log4j.logger.org.springframework.security=info,stdout'
	 * to log4j.*.properties to see logs )
	 */
	useLogger = false

	/** use RequestMap from DomainClass */
	useRequestMapDomainClass = true

	/** Requestmap domain class (if useRequestMapDomainClass = true) */
	requestMapClass = 'Requestmap'
	requestMapPathField = 'url'
	requestMapConfigAttributeField = 'configAttribute'

	/** 
	 * if useRequestMapDomainClass is false, set request map pattern in string
	 * see example below
	 */
	requestMapString = """
	CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
	PATTERN_TYPE_APACHE_ANT

	/login/**=IS_AUTHENTICATED_ANONYMOUSLY
	/admin/**=ROLE_USER
	/book/test/**=IS_AUTHENTICATED_FULLY
	/book/**=ROLE_SUPERVISOR
	/**=IS_AUTHENTICATED_ANONYMOUSLY
	"""

	// basic auth
	realmName = 'Grails Realm'

	/** use basicProcessingFilter */
	basicProcessingFilter = false
	/** use switchUserProcessingFilter */
	switchUserProcessingFilter = false
	swswitchUserUrl = '/j_spring_security_switch_user'
	swexitUserUrl = '/j_spring_security_exit_user'
	swtargetUrl = '/'

	/**use email notification while registration*/
	useMail = false
	mailHost = 'localhost'
	mailUsername = 'user@localhost'
	mailPassword = 'sungod'
	mailProtocol = 'smtp'
	mailFrom = 'user@localhost'
	mailPort = 25

	/** default user's role for user registration */
	defaultRole='ROLE_USER'

	// OpenId
	useOpenId = false
	openIdNonceMaxSeconds = 300 // max time between auth start and end

	// LDAP/ActiveDirectory
	useLdap = false
	ldapRetrieveGroupRoles = true
	ldapRetrieveDatabaseRoles = false
	ldapSearchSubtree = true
	ldapGroupRoleAttribute = 'cn'
	ldapPasswordAttributeName = 'userPassword'
}
