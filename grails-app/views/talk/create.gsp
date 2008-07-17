

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="talk.create" default="Create Talk" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="talk.list" default="Talk List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="talk.create" default="Create Talk" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${talk}">
            <div class="errors">
                <g:renderErrors bean="${talk}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="event"><g:message code="talk.event" default="Event" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:talk,field:'event','errors')}">
                                    <g:select optionKey="id" from="${Event.list()}" name="event.id" value="${talk?.event?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title"><g:message code="talk.title" default="Title" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:talk,field:'title','errors')}">
                                    <input type="text" maxlength="200" id="title" name="title" value="${fieldValue(bean:talk,field:'title')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="summary"><g:message code="talk.summary" default="Summary" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:talk,field:'summary','errors')}">
                                    <textarea rows="5" cols="40" name="summary">${fieldValue(bean:talk, field:'summary')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate"><g:message code="talk.startDate" default="Start Date" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:talk,field:'startDate','errors')}">
                                    <g:datePicker name="startDate" value="${talk?.startDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate"><g:message code="talk.endDate" default="End Date" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:talk,field:'endDate','errors')}">
                                    <g:datePicker name="endDate" value="${talk?.endDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="location"><g:message code="talk.location" default="Location" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:talk,field:'location','errors')}">
                                    <input type="text" maxlength="100" id="location" name="location" value="${fieldValue(bean:talk,field:'location')}"/>
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
