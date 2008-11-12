<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="event.show" default="Show Event" /></title>
		<util:modalIncludes />
    </head>
	<body>
		<div class="body">
			<g:if test="${event != null}">
				<g:render template="register"/>
				<br/>
				<g:render template="/event/eventDetail"/>
				<br />
				<g:if test="${event.talks.size() > 0}">
					<h2><g:message code="event.talks" default="Event Talks" /></h2>
					<br />
					<table>
						<tbody>
							<tr class='prop'>
								<th scope="col" valign='top' class='name'>
									<g:message code="talk.title" default="Name" />
								</th>
							</tr>
							<g:each var="talk" in="${event.talks}">
								<tr class="prop">
	                                <td valign="top">
	                                    ${talk.title}
	                                </td>
	                            </tr>
							</g:each>
						</tbody>
					</table>
				</g:if>
				<g:render template="register"/>
			</g:if>
		</div>
    </body>
</html>
