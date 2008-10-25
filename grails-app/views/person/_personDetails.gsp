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
            <td valign="top" class="value">${person?.description}</td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="person.blog" default="Blog" />:</td>
            <td valign="top" class="value">${fieldValue(bean:person, field:'blog')}</td>
        </tr>
		<g:if test="${showRoles}">
			<tr class="prop">
                <td valign="top" class="name"><g:message code="person.authorities" default="Authorities" />:</td>
                <td valign="top" class="value">${fieldValue(bean:person, field:'authorities')}</td>
            </tr>
		</g:if>
    </tbody>
</table>