<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="event.show" default="Show Event" /></title>
    </head>
    <body>
        <div class="body">
            
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>

			<g:if test="${event != null}">
				<g:render template="/event/eventDetail"/>
				<br />
				<h3><g:message code="talks.attendee" default="Talks I attendee" /></h3>

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


						<g:each var="talk" in="${attendee.talks}">
							<g:if test="${talk.status == Status.OPEN}">
								<tr class="prop">
	                                <td valign="top">
	                                    ${talk.title}
	                                </td>
									<td valign="top">
	                                    <g:checkBox name='talk_$talk.id'></g:checkBox>
	                                </td>
	                            </tr>
							</g:if>
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

	                </tbody>
	            </table>

				<br />
				<h3><g:message code="talks.speaker" default="Talks I do" /></h3>
			</g:if>

			
			
			
            
        </div>
    </body>
</html>
