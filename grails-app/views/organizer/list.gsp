

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="organizer.list" default="Organizer List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="organizer.new" default="New Organizer" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="organizer.list" default="Organizer List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" titleKey="organizer.id" />
                        
                   	        <g:sortableColumn property="name" title="Name" titleKey="organizer.name" />
                        
                   	        <g:sortableColumn property="website" title="Website" titleKey="organizer.website" />
                        
                   	        <g:sortableColumn property="info" title="Info" titleKey="organizer.info" />
                        
                   	        <g:sortableColumn property="geolocation" title="Geolocation" titleKey="organizer.geolocation" />
                        
                   	        <g:sortableColumn property="logoUrl" title="Logo Url" titleKey="organizer.logoUrl" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${organizerList}" status="i" var="organizer">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${organizer.id}">${fieldValue(bean:organizer, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:organizer, field:'name')}</td>
                        
                            <td>${fieldValue(bean:organizer, field:'website')}</td>
                        
                            <td>${fieldValue(bean:organizer, field:'info')}</td>
                        
                            <td>${fieldValue(bean:organizer, field:'geolocation')}</td>
                        
                            <td>${fieldValue(bean:organizer, field:'logoUrl')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Organizer.count()}" />
            </div>
        </div>
    </body>
</html>
