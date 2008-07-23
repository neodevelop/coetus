

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="event.show" default="Show Event" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="event.show" default="Show Event" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.name" default="Name" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.description" default="Description" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'description')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.startTime" default="Start Time" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'startTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.endTime" default="End Time" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'endTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.allDay" default="All Day" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'allDay')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.location" default="Location" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'location')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.necessaryRegistry" default="Necessary Registry" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'necessaryRegistry')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.showStatus" default="Show Status" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:event, field:'showStatus')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="event.status" default="Status" />:</td>
                            
                            <td valign="top" class="value">${event?.status?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${event?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
