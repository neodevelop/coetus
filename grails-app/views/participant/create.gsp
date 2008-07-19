

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="participant.create" default="Create Participant" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="participant.list" default="Participant List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="participant.create" default="Create Participant" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${participant}">
            <div class="errors">
                <g:renderErrors bean="${participant}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="company"><g:message code="participant.company" default="Company" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:participant,field:'company','errors')}">
                                    <input type="text" id="company" name="company" value="${fieldValue(bean:participant,field:'company')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="participant.email" default="Email" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:participant,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:participant,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="participant.name" default="Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:participant,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:participant,field:'name')}"/>
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
