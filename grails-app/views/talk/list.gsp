

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="talk.list" default="Talk List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="talk.new" default="New Talk" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="talk.list" default="Talk List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" titleKey="talk.id" />
                        
                   	        <th><g:message code="talk.event" default="Event" /></th>
                   	    
                   	        <g:sortableColumn property="title" title="Title" titleKey="talk.title" />
                        
                   	        <g:sortableColumn property="summary" title="Summary" titleKey="talk.summary" />
                        
                   	        <g:sortableColumn property="startDate" title="Start Date" titleKey="talk.startDate" />
                        
                   	        <g:sortableColumn property="endDate" title="End Date" titleKey="talk.endDate" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${talkList}" status="i" var="talk">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${talk.id}">${fieldValue(bean:talk, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:talk, field:'event')}</td>
                        
                            <td>${fieldValue(bean:talk, field:'title')}</td>
                        
                            <td>${fieldValue(bean:talk, field:'summary')}</td>
                        
                            <td>${fieldValue(bean:talk, field:'startDate')}</td>
                        
                            <td>${fieldValue(bean:talk, field:'endDate')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Talk.count()}" />
            </div>
        </div>
    </body>
</html>
