

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="event.show" default="Show Event" /></title>
    </head>
    <body>
        <div class="body">
            
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>


			<g:isNotLoggedIn><div class="message"><g:message code="signin" default="You must login or sign in to register" /></div></g:isNotLoggedIn>

			

			<g:if test="${event != null}">
				<g:isLoggedIn>
					Registrrando al avebto
				</g:isLoggedIn>
	            
			</g:if>
			
            
        </div>
    </body>
</html>
