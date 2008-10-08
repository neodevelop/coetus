<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="person.show" default="Show Person" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="person.list" default="Person List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="person.new" default="New Person" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="person.show" default="Show Person" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
						<tr class="prop">
                            <td valign="top" class="name"><g:message code="person.avatar" default="Avatar" /></td>
                            
                            <td valign="top" class="value"><avatar:gravatar email="${fieldValue(bean:person, field:'email')}" size="80" /></td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.username" default="Username" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:person, field:'username')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.userRealName" default="User Real Name" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:person, field:'userRealName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.company" default="Company" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:person, field:'company')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.description" default="Description" />:</td>
                            
                            <td valign="top" class="value">${person?.description?.encodeAsTextile()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.blog" default="Blog" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:person, field:'blog')}</td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.authorities" default="Authorities" />:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:person, field:'authorities')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
			<g:ifAllGranted role="ROLE_ADMIN">
            	<div class="buttons">
                	<g:form>
                    	<input type="hidden" name="id" value="${person?.id}" />
                    	<span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                    	<span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
                	</g:form>
            	</div>
			</g:ifAllGranted>
			<!-- 
			<g:if test="${loggedInUserInfo(field:'username')}">
            	<div class="buttons">
                	<g:form>
                    	<input type="hidden" name="id" value="${person?.id}" />
                    	<span class="button"><g:actionSubmit class="edit" action="Edit" value="${message(code:'edit', 'default':'Edit')}" /></span>
                	</g:form>
            	</div>
			</g:if>
			-->
        </div>
    </body>
</html>
