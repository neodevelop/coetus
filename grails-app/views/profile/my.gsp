<html>
	<head>
		<meta name='layout' content='main' />
		<title><g:message code="person.profile" default="My profile" />-${person?.userRealName}</title>
	</head>
	<body>
<<<<<<< .mine
		<h1><g:message code="auth.editUser" default="Edit User" /></h1>
		<br>
		<div class="box">
				<p>
					<label for="username"><g:message code="me.username" default="Username" />:</label>:<br/>
					<input type="text" size="42" maxlength="30" id="username" name="username" value="${fieldValue(bean:me,field:'username')}"/>
				</p>
				<p>
					<label for="userRealName"><g:message code="me.userRealName" default="User Real Name" />:</label><br/>
					<input type="text" size="42" maxlength="200" id="userRealName" name="userRealName" value="${fieldValue(bean:me,field:'userRealName')}"/>
				</p>
				<p>
					<p><label for="description"><g:message code="me.description" default="Description" /> (<a href="http://hobix.com/textile" target="_blank">Textile enabled</a>):</label><br />
					<textarea rows="5" cols="40" name="description">${fieldValue(bean:me, field:'description')}</textarea></p>
				</p>
				<g:if test="${newRecord}">
					<g:render template="../me/mePasswordForm" />
				</g:if>
				<p>
					<label for="email"><g:message code="me.email" default="Email" />:</label><br/>
					<input type="text" size="42" maxlength="100" id="email" name="email" value="${fieldValue(bean:me,field:'email')}"/>
					<span class="meta-info">Associate this email address with an avatar for free at <a href="http://gravatar.com/" target="_blank">gravatar.com</a></span>
				</p>
				<p>
					<label for="company"><g:message code="me.company" default="Company" />:</label><br/>
					<input type="text" size="42" maxlength="100" id="company" name="company" value="${fieldValue(bean:me,field:'company')}"/>
				</p>
				<p>
					<label for="blog"><g:message code="me.blog" default="Blog" />:</label><br/>
					<input type="text" size="42" maxlength="100" id="blog" name="blog" value="${fieldValue(bean:me,field:'blog')}"/>
				</p>
				<p>
			        <label for="canBeContactedViaEmail">Allow people to contact me via email:</label>
					<g:checkBox name="canBeContactedViaEmail" id="canBeContactedViaEmail" value="${fieldValue(bean:me,field:'canBeContactedViaEmail')}" />
			    </p>
				<g:ifAllGranted role="ROLE_ADMIN">
					<p><label for="enabled"><g:message code="me.enabled" default="Enabled" />:</label><br />
					<g:checkBox name="enabled" value="${me?.enabled}" ></g:checkBox></p>


					<ul>
					<g:each var="a" in="${me?.authorities?}">
					    <li><g:link controller="authority" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
					<g:link controller="authority" params="['me.id':me?.id]" action="create">Add Authority</g:link>

				</g:ifAllGranted>
				<g:if test="${newRecord}">
					<p>
						<label for='code'><g:message code="me.enterCode" default="Enter Code" />: </label><br/>
						<input type="text" name="captcha" size="8" size="42" />
				      	<img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/>
					</p>
				</g:if>
		</div>
=======
		<g:render template="../person/personForm" />
		<g:if test="${person?.hasLocation()}">
			<g:render template="../person/location" />
		</g:if>
		
		
		
>>>>>>> .r130
	</body>
</html>
