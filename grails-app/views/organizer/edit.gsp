

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="organizer.edit" default="Edit Organizer" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="organizer.list" default="Organizer List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="organizer.new" default="New Organizer" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="organizer.edit" default="Edit Organizer" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${organizer}">
            <div class="errors">
                <g:renderErrors bean="${organizer}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${organizer?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="organizer.name" default="Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'name','errors')}">
                                    <textarea rows="5" cols="40" name="name">${fieldValue(bean:organizer, field:'name')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="website"><g:message code="organizer.website" default="Website" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'website','errors')}">
                                    <textarea rows="5" cols="40" name="website">${fieldValue(bean:organizer, field:'website')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="info"><g:message code="organizer.info" default="Info" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'info','errors')}">
                                    <textarea rows="5" cols="40" name="info">${fieldValue(bean:organizer, field:'info')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="geolocation"><g:message code="organizer.geolocation" default="Geolocation" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'geolocation','errors')}">
                                    <textarea rows="5" cols="40" name="geolocation">${fieldValue(bean:organizer, field:'geolocation')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="logoUrl"><g:message code="organizer.logoUrl" default="Logo Url" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'logoUrl','errors')}">
                                    <textarea rows="5" cols="40" name="logoUrl">${fieldValue(bean:organizer, field:'logoUrl')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailContact"><g:message code="organizer.emailContact" default="Email Contact" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'emailContact','errors')}">
                                    <textarea rows="5" cols="40" name="emailContact">${fieldValue(bean:organizer, field:'emailContact')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated"><g:message code="organizer.dateCreated" default="Date Created" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'dateCreated','errors')}">
                                    <g:datePicker name="dateCreated" value="${organizer?.dateCreated}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated"><g:message code="organizer.lastUpdated" default="Last Updated" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'lastUpdated','errors')}">
                                    <g:datePicker name="lastUpdated" value="${organizer?.lastUpdated}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy"><g:message code="organizer.createdBy" default="Created By" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'createdBy','errors')}">
                                    <g:select optionKey="id" from="${Person.list()}" name="createdBy.id" value="${organizer?.createdBy?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="people"><g:message code="organizer.people" default="People" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:organizer,field:'people','errors')}">
                                    
<ul>
<g:each var="p" in="${organizer?.people?}">
    <li><g:link controller="person" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="person" params="['organizer.id':organizer?.id]" action="create">Add Person</g:link>

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
