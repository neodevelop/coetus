<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="event.create" default="Create event" /></title>
	</head>
	<body>
		<h1><g:message code="event.create" default="Create event" /></h1>
		<g:render template="../util/showErrors" model="['bean':event]" />
		<g:form action="saveEvent" method="post" name='registerForm' >
			<g:render template="../event/eventForm" model="['newRecord':true]"/>
			
			<div class="buttons">
				<span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
			</div>
			<script type='text/javascript'>
				<!--
				(function(){
					document.forms['registerForm'].elements['name'].focus();
				})();
				// -->
			</script>
		</g:form>
	</body>
</html>
