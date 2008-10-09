<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="event.show" default="Show Event" /></title>
	</head>
	<body>
		<div class="body">
			
			<g:if test="${event != null}">
				<h1><g:message code="event.register" default="choose the talks to which ATTEND" /></h1>
				<br />
				<g:form method="post" >
					<g:render template="/event/eventDetail"/>
					<br />
					<g:if test="${event.talks.size() > 0}">
						<h3><g:message code="talks.choose" default="choose the talks to which ATTEND" /></h3>

						<table>
							<tbody>
								<tr class='prop'>
									<th scope="col" valign='top' class='name'>
										<g:message code="talk.title" default="Name" />
									</th>
									<th scope="col" valign='top' class='name'>
										<div><g:message code="talk.attendee" default="Attendee" /></div>
									</th>
								</tr>

								<g:each var="talk" in="${event.talks}">
									<g:if test="${talk.status == Status.OPEN}">
										<tr class="prop">
			                                <td valign="top">
			                                    ${talk.title}
			                                </td>
											<td valign="top">
			                                    <g:checkBox name='talk_$talk.id' />
			                                </td>
			                            </tr>
									</g:if>
								</g:each>

	                        </tbody>
	                    </table>
					</g:if>
					

					<div class="buttons">
						<input type="hidden" name="id" value="${event?.id}" />
	                    <span class="button"><g:actionSubmit class="save" action="doRegistry" value="${message(code:'update', 'default':'Update')}" /></span>
	                </div>	
					
				</g:form>
			</g:if>
            
        </div>
    </body>
</html>
