

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="organizer.show" default="Show Organizer" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="organizer.list" default="Organizer List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="organizer.new" default="New Organizer" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="organizer.show" default="Show Organizer" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.id" default="Id" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.name" default="Name" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.website" default="Website" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'website')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.info" default="Info" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'info')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.geolocation" default="Geolocation" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'geolocation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.logoUrl" default="Logo Url" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'logoUrl')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.emailContact" default="Email Contact" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'emailContact')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.dateCreated" default="Date Created" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'dateCreated')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.lastUpdated" default="Last Updated" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'lastUpdated')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.createdBy" default="Created By" />:</td>
                            
                            <td valign="top" class="value"><g:link controller="person" action="show" id="${organizer?.createdBy?.id}">${organizer?.createdBy?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="organizer.people" default="People" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:organizer, field:'people')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${organizer?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
