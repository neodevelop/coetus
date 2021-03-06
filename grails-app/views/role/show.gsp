<html>
	<head>
		<meta name="layout" content="main" />
		<title>Show Authority</title>
	</head>

	<body>

		<div class="nav">
			<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
			<span class="menuButton"><g:link class="list" action="list">Authority List</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">New Authority</g:link></span>
		</div>

		<div class="body">
			<h1>Show Authority</h1>
			<div class="dialog">
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name">Id:</td>
						<td valign="top" class="value">${authority.id}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Authority Name:</td>
						<td valign="top" class="value">${authority.authority.substring(5).toLowerCase()}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Description:</td>
						<td valign="top" class="value">${authority.description}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">People:</td>
						<td valign="top" class="value">${authority.people}</td>
					</tr>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<g:form>
					<input type="hidden" name="id" value="${authority?.id}" />
					<span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
					<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
				</g:form>
			</div>

		</div>

	</body>
</html>

