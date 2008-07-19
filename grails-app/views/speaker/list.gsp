

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="speaker.list" default="Speaker List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="speaker.new" default="New Speaker" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="speaker.list" default="Speaker List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" titleKey="speaker.id" />
                        
                   	        <g:sortableColumn property="name" title="Name" titleKey="speaker.name" />
                        
                   	        <g:sortableColumn property="bio" title="Bio" titleKey="speaker.bio" />
                        
                   	        <g:sortableColumn property="blog" title="Blog" titleKey="speaker.blog" />
                        
                   	        <g:sortableColumn property="email" title="Email" titleKey="speaker.email" />
                        
                   	        <g:sortableColumn property="company" title="Company" titleKey="speaker.company" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${speakerList}" status="i" var="speaker">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${speaker.id}">${fieldValue(bean:speaker, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:speaker, field:'name')}</td>
                        
                            <td>${fieldValue(bean:speaker, field:'bio')}</td>
                        
                            <td>${fieldValue(bean:speaker, field:'blog')}</td>
                        
                            <td>${fieldValue(bean:speaker, field:'email')}</td>
                        
                            <td>${fieldValue(bean:speaker, field:'company')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Speaker.count()}" />
            </div>
        </div>
    </body>
</html>
