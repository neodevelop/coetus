<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="person.create" default="Create Person" /></title>
	</head>
	<body>
		<h1><g:message code="person.create" default="Create Person" /></h1>
		<br/>
		<g:render template="../util/showErrors" model="['bean':person]" />
		<g:form action="save" method="post" name='registerForm' >
			<g:render template="../person/personForm" model="['newRecord':true]"/>
			
			<div class="buttons">
				<span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
			</div>
			<script type='text/javascript'>
				<!--
				(function(){
					document.forms['registerForm'].elements['username'].focus();
				})();
				// -->
			</script>
		</g:form>
	</body>
</html>
