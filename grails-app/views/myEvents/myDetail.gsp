<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="event.show" default="Show Event" /></title>
    </head>
    <body>
        <div class="body">
            
			<g:if test="${event != null}">
				<g:render template="/event/eventDetail"/>
				<br />
				<g:if test="${event.talks.size() > 0}">
					<h3><g:message code="talks.attendee" default="Talks I attendee" /></h3>

					<table>
		                <tbody>
							<tr class='prop'>
							<th scope="col" valign='top' class='name'>
							<g:message code="talk.title" default="Name" />
							</th>
							<th scope="col" valign='top' class='name'>
							<g:message code="delete" default="Delete" />
							</th>
		                    </tr>


							<g:each var="talk" in="${attendee.talks}">
									<tr class="prop">
		                                <td valign="top">
		                                    ${talk.title}
		                                </td>

										<td valign="top">
											<a href="${createLink(action:'deleteTalk',params:[talkId:talk.id,eventId:event.id])}"><g:message code="delete" default="Delete" /></a>
		                                </td>
		                            </tr>
							</g:each>

		                </tbody>
		            </table>

					<br />
					<h3><g:message code="talks.pendingToAttend" default="Talks pending to attend" /></h3>

					<table>
		                <tbody>
							<tr class='prop'>
							<th scope="col" valign='top' class='name'>
							<g:message code="talk.title" default="Name" />
							</th>
							<th scope="col" valign='top' class='name'>
							<div>
									<g:message code="talk.attendee" default="Attendee" />
								</div>
							</th>
		                    </tr>

							<g:form method="post" >
							<g:each var="talk" in="${talksPendingToAttend}">
									<tr class="prop">
		                                <td valign="top">
		                                    ${talk.title}
		                                </td>
										<td valign="top">
		                                    <g:checkBox name='talk_$talk.id'></g:checkBox>
		                                </td>
		                            </tr>
							</g:each>
							<tr class="prop">
	                            <td valign="top">

	                            </td>
								<td valign="top">
	                                <input type="hidden" name="id" value="${event?.id}" />
				                    <span class="button"><g:actionSubmit class="save" action="addTalkToAttend" value="${message(code:'update', 'default':'Update')}" /></span>
	                            </td>
	                        </tr>
							</g:form>
		                </tbody>
		            </table>
				</g:if>
				
		

			</g:if>

			
			
			
            
        </div>
    </body>
</html>
