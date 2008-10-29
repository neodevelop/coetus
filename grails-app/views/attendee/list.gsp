<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="attendee.list" default="Attendee List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="attendee.new" default="New Attendee" /></g:link></span>
        </div>
        <div class="body">
			<g:if test="${attendeeList != null}">
				<h1><g:message code="attendee.list" default="Attendee List" /></h1>
	            <div class="list">
	                <table>
	                    <thead>
	                        <tr>

	                   	        <g:sortableColumn property="id" title="Id" titleKey="attendee.id" />

	                   	        <th><g:message code="attendee.event" default="Event" /></th>

	                   	        <th><g:message code="attendee.person" default="Person" /></th>

	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${attendeeList}" status="i" var="attendee">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

	                            <td><g:link action="show" id="${attendee.id}">${fieldValue(bean:attendee, field:'id')}</g:link></td>

	                            <td>${fieldValue(bean:attendee, field:'event')}</td>

	                            <td>${fieldValue(bean:attendee, field:'person')} <br /> ${fieldValue(bean:attendee.person, field:'email')}</td>

	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	            </div>
	            <div class="paginateButtons">
	                <g:paginate total="${Attendee.count()}" />
	            </div>
			</g:if>
        </div>
    </body>
</html>
