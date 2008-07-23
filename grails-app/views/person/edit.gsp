

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="person.edit" default="Edit Person" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="person.list" default="Person List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="person.new" default="New Person" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="person.edit" default="Edit Person" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${person}">
            <div class="errors">
                <g:renderErrors bean="${person}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${person?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="username"><g:message code="person.username" default="Username" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
                                    <input type="text" maxlength="30" id="username" name="username" value="${fieldValue(bean:person,field:'username')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userRealName"><g:message code="person.userRealName" default="User Real Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'userRealName','errors')}">
                                    <input type="text" maxlength="200" id="userRealName" name="userRealName" value="${fieldValue(bean:person,field:'userRealName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="company"><g:message code="person.company" default="Company" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'company','errors')}">
                                    <input type="text" maxlength="100" id="company" name="company" value="${fieldValue(bean:person,field:'company')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="passwd"><g:message code="person.passwd" default="Passwd" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
                                    <textarea rows="5" cols="40" name="passwd">${fieldValue(bean:person, field:'passwd')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="person.email" default="Email" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:person,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="person.description" default="Description" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'description','errors')}">
                                    <textarea rows="5" cols="40" name="description">${fieldValue(bean:person, field:'description')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="blog"><g:message code="person.blog" default="Blog" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'blog','errors')}">
                                    <textarea rows="5" cols="40" name="blog">${fieldValue(bean:person, field:'blog')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="enabled"><g:message code="person.enabled" default="Enabled" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
                                    <g:checkBox name="enabled" value="${person?.enabled}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="authorities"><g:message code="person.authorities" default="Authorities" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'authorities','errors')}">
                                    
<ul>
<g:each var="a" in="${person?.authorities?}">
    <li><g:link controller="authority" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="authority" params="['person.id':person?.id]" action="create">Add Authority</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailShow"><g:message code="person.emailShow" default="Email Show" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'emailShow','errors')}">
                                    <g:checkBox name="emailShow" value="${person?.emailShow}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="pass"><g:message code="person.pass" default="Pass" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'pass','errors')}">
                                    <input type="text" name="pass" id="pass" value="${fieldValue(bean:person,field:'pass')}" />
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
