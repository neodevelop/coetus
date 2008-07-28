<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
		<meta name='layout' content='main' />
		<title>Ingresa</title>
	</head>
	<body>
		<div class="body">
			<div class="body">
				<h1><g:message code="auth.welcome" default="Please Login..." /></h1>
				<br>

				
				<g:if test='${flash.message}'>
					<div class="errors">${flash.message}</div>
				</g:if>
				
				
				<form action='${request.contextPath}/j_spring_security_check' method='POST' id='loginForm'>
					<table>
                        <tbody>                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="auth.loginId" default="Login ID" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'name','errors')}">
                                    <input type='text' name='j_username' id='j_username'  />
                                </td>
                            </tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="auth.password" default="Password" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'name','errors')}">
                                    <input type='password' name='j_password' id='j_password' />
                                </td>
                            </tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="auth.rememberMe" default="Remember Me" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:event,field:'name','errors')}">
                                    <input type='checkbox' id='remember_me' name='_spring_security_remember_me'>
                                </td>
                            </tr>
						</tbody>
					</table>
					<div class="buttons">
                    <span class="button">
						<input class="home" type="submit" value="${message(code:'auth.login', 'default':'Login')}" />
					</span>
                	</div>
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
