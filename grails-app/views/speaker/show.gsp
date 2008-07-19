

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="speaker.show" default="Show Speaker" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="speaker.list" default="Speaker List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="speaker.new" default="New Speaker" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="speaker.show" default="Show Speaker" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.id" default="Id" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.name" default="Name" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.bio" default="Bio" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'bio')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.blog" default="Blog" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'blog')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.email" default="Email" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'email')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.company" default="Company" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'company')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.publicEmail" default="Public Email" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'publicEmail')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="speaker.talks" default="Talks" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:speaker, field:'talks')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${speaker?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
