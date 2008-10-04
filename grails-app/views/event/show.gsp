<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="event.show" default="Show Event" /></title>
    </head>
    <body>
		<g:ifAllGranted role="ROLE_ADMIN">
			<div class="nav">
	            <span class="menuButton"><g:link class="list" action="list"><g:message code="event.list" default="Event List" /></g:link></span>
	            <span class="menuButton"><g:link class="create" action="create"><g:message code="event.new" default="New Event" /></g:link></span>
	        </div>
        </g:ifAllGranted>
        <div class="body">
            <h1><g:message code="event.show" default="Show Event" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:render template="eventDetail"/>
           	<g:ifAllGranted role="ROLE_ADMIN">
				<g:render template="../util/crudToolBar" model="['entity':event]"/>
	        </g:ifAllGranted>
			<g:ifNotGranted role="ROLE_ADMIN">
				<g:ifAllGranted role="ROLE_MANAGER">
						<util:verifyUserManager event="${event}">
							<g:render template="../util/crudToolBar" model="['entity':event]"/>
						</util:verifyUserManager>
				</g:ifAllGranted>
			</g:ifNotGranted>
        </div>
    </body>
</html>
