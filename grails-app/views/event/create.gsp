

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="event.create" default="Create Event" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="event.list" default="Event List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="event.create" default="Create Event" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${event}">
            <div class="errors">
                <g:renderErrors bean="${event}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="event.name" default="Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'name','errors')}">
                                    <input type="text" maxlength="200" id="name" name="name" value="${fieldValue(bean:event,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="event.description" default="Description" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'description','errors')}">
                                    <textarea rows="5" cols="40" name="description">${fieldValue(bean:event, field:'description')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startTime"><g:message code="event.startTime" default="Start Time" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'startTime','errors')}">
                                    <g:datePicker name="startTime" value="${event?.startTime}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endTime"><g:message code="event.endTime" default="End Time" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'endTime','errors')}">
                                    <g:datePicker name="endTime" value="${event?.endTime}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="allDay"><g:message code="event.allDay" default="All Day" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'allDay','errors')}">
                                    <g:checkBox name="allDay" value="${event?.allDay}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="location"><g:message code="event.location" default="Location" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'location','errors')}">
                                    <input type="text" maxlength="200" id="location" name="location" value="${fieldValue(bean:event,field:'location')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="necessaryRegistry"><g:message code="event.necessaryRegistry" default="Necessary Registry" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'necessaryRegistry','errors')}">
                                    <g:checkBox name="necessaryRegistry" value="${event?.necessaryRegistry}" ></g:checkBox>
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
