<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="event.create" default="Create event" /></title>
	</head>
	<body>
		<h1><g:message code="event.create" default="Create event" /></h1>
		<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${event}">
			<div class="errors">
				<g:renderErrors bean="${event}" as="list" />
			</div>
		</g:hasErrors>
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
