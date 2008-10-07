<html>
	<head>
		<meta name='layout' content='main' />
		<title><g:message code="person.profile" default="My profile" />-${person?.userRealName}</title>
	</head>
	<body>
		<g:form method="post">
		<g:render template="../person/personForm" />
		<div class="buttons">
			<span class="button"><g:actionSubmit class="save" value="${message(code:'update', 'default':'Update')}" action="update"/></span>
		</div>
		</g:form>
		<!-- 
		<g:if test="${person?.hasLocation()}">
			<g:render template="../person/location" />
		</g:if>
		-->
	</body>
</html>
