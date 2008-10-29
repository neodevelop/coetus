<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="attendee.edit" default="Edit Attendee" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="attendee.list" default="Attendee List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="attendee.new" default="New Attendee" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="attendee.edit" default="Edit Attendee" /></h1>
            <g:render template="../util/showErrors" model="['bean':attendee]" />
            <g:form method="post" >
                <input type="hidden" name="id" value="${attendee?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="event"><g:message code="attendee.event" default="Event" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attendee,field:'event','errors')}">
                                    <g:select optionKey="id" from="${Event.list()}" name="event.id" value="${attendee?.event?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="person"><g:message code="attendee.person" default="Person" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attendee,field:'person','errors')}">
                                    <g:select optionKey="id" from="${Person.list()}" name="person.id" value="${attendee?.person?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="talks"><g:message code="attendee.talks" default="Talks" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attendee,field:'talks','errors')}">
                                    
<ul>
<g:each var="t" in="${attendee?.talks?}">
    <li><g:link controller="talk" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="talk" params="['attendee.id':attendee?.id]" action="create">Add Talk</g:link>

                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="Update" value="${message(code:'update', 'default':'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
