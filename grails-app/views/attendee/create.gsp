<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="attendee.create" default="Create Attendee" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="attendee.list" default="Attendee List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="attendee.create" default="Create Attendee" /></h1>
            <g:render template="../util/showErrors" model="['bean':attendee]" />
            <g:form action="save" method="post" >
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
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
