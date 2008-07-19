

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="participant.edit" default="Edit Participant" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="participant.list" default="Participant List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="participant.new" default="New Participant" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="participant.edit" default="Edit Participant" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${participant}">
            <div class="errors">
                <g:renderErrors bean="${participant}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${participant?.id}" />
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
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="talks"><g:message code="participant.talks" default="Talks" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:participant,field:'talks','errors')}">
                                    
<ul>
<g:each var="t" in="${participant?.talks?}">
    <li><g:link controller="talk" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="talk" params="['participant.id':participant?.id]" action="create">Add Talk</g:link>

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
