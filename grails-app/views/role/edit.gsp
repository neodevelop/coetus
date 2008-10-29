<html>
	<head>
		<meta name="layout" content="main" />
		<title>Edit Authority</title>
	</head>
	<body>
		<div class="nav">
			<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
			<span class="menuButton"><g:link class="list" action="list">Authority List</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">New Authority</g:link></span>
		</div>
		<div class="body">
			<h1>Edit Authority</h1>
			<g:render template="../util/showErrors" model="['bean':authority]" />
			<div class="prop">
				<span class="name">Id:</span>
				<span class="value">${authority?.id}</span>
			</div>
			<g:form method="post">
				<input type="hidden" name="id" value="${authority?.id}" />
				<div class="dialog">
					<table>
					<tbody>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="authority">Authority Name:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
								<input type="text" id="authority" name="authority" value="${authority?.authority?.substring(5)?.toLowerCase()?.encodeAsHTML()}"/>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="description">Description:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:authority,field:'description','errors')}">
								<input type="text" id="description" name="description" value="${authority?.description?.encodeAsHTML()}"/>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="people">People:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:authority,field:'people','errors')}">
								<ul>
								<g:each var="p" in="${authority?.people?}">
									<li>${p}</li>
								</g:each>
								</ul>
							</td>
						</tr>
					</tbody>
					</table>
				</div>

				<div class="buttons">
					<span class="button"><g:actionSubmit class="save" value="Update" /></span>
					<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
				</div>

			</g:form>
		</div>
	</body>
</html>
