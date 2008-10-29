<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="attendee.show" default="Show Attendee" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="attendee.list" default="Attendee List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="attendee.new" default="New Attendee" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="attendee.show" default="Show Attendee" /></h1>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="attendee.id" default="Id" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attendee, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="attendee.event" default="Event" />:</td>
                            
                            <td valign="top" class="value"><g:link controller="event" action="show" id="${attendee?.event?.id}">${attendee?.event?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="attendee.person" default="Person" />:</td>
                            
                            <td valign="top" class="value"><g:link controller="person" action="show" id="${attendee?.person?.id}">${attendee?.person?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="attendee.talks" default="Talks" />:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="t" in="${attendee.talks}">
                                    <li><g:link controller="talk" action="show" id="${t.id}">${t.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${attendee?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
