

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

				<g:if test="${event.status == Status.OPEN}">
					<div class="buttons">
			            <g:form>
			                <input type="hidden" name="id" value="${event?.id}" />
							<g:link controller="event" action="register" id="${event.id}">
								<span>
									<g:message code="event.register" default="Register" />
								</span>
							</g:link>
			            </g:form>
			        </div>
				</g:if>
			</g:if>
			
			
            
        </div>
    </body>
</html>
