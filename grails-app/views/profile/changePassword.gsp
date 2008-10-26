<html>
	<head>
		<meta name='layout' content='main' />
		<title><g:message code="password.change" default="Change Password" />-${person?.userRealName}</title>
	</head>
	<body>
		<g:render template="../util/showErrors" model="['bean':changePassword]" />
		<g:form method="post" name="changePasswordForm">
			<g:render template="../person/changePasswordForm" model="['bean':changePassword]" />
			<div class="buttons">
				<span class="button">
					<g:actionSubmit class="save" value="${message(code:'update', 'default':'Update')}" action="updatePassword"/>
				</span>
			</div>
			<script type='text/javascript'>
				<!--
				(function(){
					document.forms['changePasswordForm'].elements['actualPasswd'].focus();
				})();
				// -->
			</script>
		</g:form>
	</body>
</html>
