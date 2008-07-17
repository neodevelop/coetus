

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="speaker.edit" default="Edit Speaker" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="speaker.list" default="Speaker List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="speaker.new" default="New Speaker" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="speaker.edit" default="Edit Speaker" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${speaker}">
            <div class="errors">
                <g:renderErrors bean="${speaker}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${speaker?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="speaker.name" default="Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:speaker,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bio"><g:message code="speaker.bio" default="Bio" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'bio','errors')}">
                                    <textarea rows="5" cols="40" name="bio">${fieldValue(bean:speaker, field:'bio')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="blog"><g:message code="speaker.blog" default="Blog" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'blog','errors')}">
                                    <input type="text" id="blog" name="blog" value="${fieldValue(bean:speaker,field:'blog')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="speaker.email" default="Email" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:speaker,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="publicEmail"><g:message code="speaker.publicEmail" default="Public Email" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'publicEmail','errors')}">
                                    <g:checkBox name="publicEmail" value="${speaker?.publicEmail}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="talks"><g:message code="speaker.talks" default="Talks" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'talks','errors')}">
                                    
<ul>
<g:each var="t" in="${speaker?.talks?}">
    <li><g:link controller="talk" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="talk" params="['speaker.id':speaker?.id]" action="create">Add Talk</g:link>

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
