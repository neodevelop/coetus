<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="person.passwd.forgot" default="Forgot your password..." /></title>
	</head>
	<body>
		<h1><g:message code="person.passwd.forgot" default="Forgot your password..." /></h1>
		<br/>
		<g:hasErrors bean="${person}">
			<div class="errors">
				<g:renderErrors bean="${person}" as="list" />
			</div>
		</g:hasErrors>
		<g:form action="recovery" method="post" name='recoveryForm' >
			<div class="box">
			<p>
					<label for="email"><g:message code="person.email.provide" default="Please provide the Email address that you use to register..." /></label><br/>
					<input type="text" size="42" maxlength="100" id="email" name="email" value="${fieldValue(bean:person,field:'email')}"/>
			</p>
			</div>
			<div class="buttons">
                <span class="button"><input class="edit" type="submit" value="${message(code:'recovery', 'default':'Recovery')}" /></span>
            </div>
		</g:form>
	</body>
</html>
