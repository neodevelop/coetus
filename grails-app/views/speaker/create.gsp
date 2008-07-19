

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="speaker.create" default="Create Speaker" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="speaker.list" default="Speaker List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="speaker.create" default="Create Speaker" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${speaker}">
            <div class="errors">
                <g:renderErrors bean="${speaker}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                                    <label for="company"><g:message code="speaker.company" default="Company" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:speaker,field:'company','errors')}">
                                    <input type="text" maxlength="100" id="company" name="company" value="${fieldValue(bean:speaker,field:'company')}"/>
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
