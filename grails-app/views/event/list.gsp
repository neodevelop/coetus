<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="event.list" default="Event List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="event.new" default="New Event" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="event.list" default="Event List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" titleKey="event.id" />
                        
                   	        <g:sortableColumn property="name" title="Name" titleKey="event.name" />
                        
                   	        <g:sortableColumn property="description" title="Description" titleKey="event.description" />
                        
                   	        <g:sortableColumn property="startTime" title="Start Time" titleKey="event.startTime" />
                        
                   	        <g:sortableColumn property="endTime" title="End Time" titleKey="event.endTime" />
                        
                   	        <g:sortableColumn property="allDay" title="All Day" titleKey="event.allDay" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${eventList}" status="i" var="event">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${event.id}">${fieldValue(bean:event, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:event, field:'name')}</td>
                        
                            <td>${fieldValue(bean:event, field:'description')}</td>
                        
                            <td>${fieldValue(bean:event, field:'startTime')}</td>
                        
                            <td>${fieldValue(bean:event, field:'endTime')}</td>
                        
                            <td>${fieldValue(bean:event, field:'allDay')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Event.count()}" />
            </div>
        </div>
    </body>
</html>
