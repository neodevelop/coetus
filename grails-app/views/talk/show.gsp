

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="talk.show" default="Show Talk" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="talk.list" default="Talk List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="talk.new" default="New Talk" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="talk.show" default="Show Talk" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.id" default="Id" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.event" default="Event" />:</td>
                            
                            <td valign="top" class="value"><g:link controller="event" action="show" id="${talk?.event?.id}">${talk?.event?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.title" default="Title" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'title')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.summary" default="Summary" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'summary')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.startDate" default="Start Date" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'startDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.endDate" default="End Date" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'endDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.location" default="Location" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'location')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.necessaryRegistry" default="Necessary Registry" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'necessaryRegistry')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.capacity" default="Capacity" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'capacity')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.displayCapacity" default="Display Capacity" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'displayCapacity')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.showStatus" default="Show Status" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:talk, field:'showStatus')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.speakers" default="Speakers" />:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${talk.speakers}">
                                    <li><g:link controller="person" action="show" id="${s.id}">${s.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="talk.status" default="Status" />:</td>
                            
                            <td valign="top" class="value">${talk?.status?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${talk?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
