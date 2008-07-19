

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="participant.list" default="Participant List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="participant.new" default="New Participant" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="participant.list" default="Participant List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" titleKey="participant.id" />
                        
                   	        <g:sortableColumn property="company" title="Company" titleKey="participant.company" />
                        
                   	        <g:sortableColumn property="email" title="Email" titleKey="participant.email" />
                        
                   	        <g:sortableColumn property="name" title="Name" titleKey="participant.name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${participantList}" status="i" var="participant">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${participant.id}">${fieldValue(bean:participant, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:participant, field:'company')}</td>
                        
                            <td>${fieldValue(bean:participant, field:'email')}</td>
                        
                            <td>${fieldValue(bean:participant, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Participant.count()}" />
            </div>
        </div>
    </body>
</html>
