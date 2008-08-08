<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
		<meta name='layout' content='main' />
		<title>Ingresa</title>
	</head>
	<body>
		<div class="body">
				<h1><g:message code="auth.no.permission" default="You dont have sufficient permissions to do the operation." /></h1>
				<br>
				
				<g:if test='${flash.message}'>
					<div class="errors">${flash.message}</div>
				</g:if>
		</div>
	</body>
</html>
