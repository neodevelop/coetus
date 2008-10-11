<html>
	<head>
		<meta name='layout' content='main' />
		<title>Ingresa</title>
	</head>
	<body>
		<div class="body">
			<h1><g:message code="auth.welcome" default="Please Login..." /></h1>
			<br>
			<g:if test='${flash.message}'>
				<div class="errors">${flash.message}</div>
			</g:if>
			
			<div class="box">
				<form action='${request.contextPath}/j_spring_security_check' method='POST' id='loginForm' class='cssform'>
					<p>
						<label for='j_username'><g:message code="auth.loginId" default="Login ID" />:</label><br/>
						<input type='text' class='text_' name='j_username' id='j_username'  />
					</p>
					<p>
						<label for='j_password'><g:message code="auth.password" default="Password" />:</label><br/>
						<input type='password' class='text_' name='j_password' id='j_password' />
					</p>
					<p>
						<label for='remember_me'><g:message code="auth.rememberMe" default="Remember Me" />:</label>
						<input type='checkbox' class='chk' id='remember_me' name='_spring_security_remember_me'>
					</p>
					<p>
						<g:link controller="register" action="forgot"><g:message code="auth.forgot.password" default="Forgot your password" /></g:link>
					</p>
					<p>
						<input type='submit' value='Login' />
					</p>
				</form>
			</div>
			<script type='text/javascript'>
				<!--
				(function(){
					document.forms['loginForm'].elements['j_username'].focus();
				})();
				// -->
			</script>
		</div>
	</body>
</html>
