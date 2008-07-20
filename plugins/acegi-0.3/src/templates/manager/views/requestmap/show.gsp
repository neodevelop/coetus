<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>Show ${requestmapDomain}</title>
	</head>
	<body>
		<div class="nav">
			<span class="menuButton"><a class="home" href="\${createLinkTo(dir:'')}">Home</a></span>
			<span class="menuButton"><g:link class="list" action="list">${requestmapDomain} List</g:link></span>
			<span class="menuButton"><g:link class="create" action="create">New ${requestmapDomain}</g:link></span>
		</div>
		<div class="body">
			<h1>Show ${requestmapDomain}</h1>
			<g:if test="\${flash.message}">
			<div class="message">\${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name">Id:</td>
						<td valign="top" class="value">\${requestmap.id}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">URL:</td>
						<td valign="top" class="value">\${requestmap.url}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Roles:</td>
<%
def a="<"
def b="%"
def c=">"
def lt="$a$b"
def gt="$b$c"
%>	
${lt}
def names = []
org.springframework.util.StringUtils.commaDelimitedListToStringArray(requestmap.configAttribute).each { role ->
	if (role.startsWith('ROLE_')) {
		names << role.substring(5).toLowerCase().encodeAsHTML()
	}
	else {
		names << role.encodeAsHTML()
	}
}
${gt}
						<td valign="top" class="value">\${names.join(',')}</td>
					</tr>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<g:form>
					<input type="hidden" name="id" value="\${requestmap?.id}" />
					<span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
					<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
				</g:form>
			</div>

		</div>
	</body>
</html>

