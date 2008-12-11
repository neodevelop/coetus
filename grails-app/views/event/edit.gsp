<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="event.edit" default="Edit Event" /></title>
		<util:modalIncludes />
	</head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="event.list" default="Event List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="event.new" default="New Event" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="event.edit" default="Edit Event" /></h1>
            <g:render template="../util/showErrors" model="['bean':event]"/>
            <g:form method="post" >
				<g:render template="../util/ud" model="['bean':event]"/>
                <g:render template="../event/eventForm" />
                <g:render template="../util/ud" model="['bean':event]"/>
            </g:form>
        </div>
    </body>
</html>
