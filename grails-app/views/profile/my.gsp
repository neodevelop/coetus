<html>
	<head>
		<meta name='layout' content='main' />
		<title>My profile-${person?.userRealName}</title>
	</head>
	<body>
		<g:render template="../person/personForm" />
		<g:if test="${person.hasLocation()}">
			<g:render template="../person/location" />
		</g:if>
		
		
		
	</body>
</html>
