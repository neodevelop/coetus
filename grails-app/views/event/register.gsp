

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


			<g:isNotLoggedIn><div class="message"><g:message code="signinOrLogin" default="You must login or sign in to register" /></div></g:isNotLoggedIn>

			<g:if test="${event != null}">
				<g:isLoggedIn>
					<g:message code="talks.choose" default="choose the talks to which ATTEND" />
					<g:form method="post" >
						
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
		
		
								<g:each var="talk" in="${event.talks}">
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
	
	
						<div class="buttons">
							<input type="hidden" name="id" value="${event?.id}" />
		                    <span class="button"><g:actionSubmit class="save" action="doRegistry" value="${message(code:'update', 'default':'Update')}" /></span>
		                </div>	
						
					</g:form>
				</g:isLoggedIn>
	            
			</g:if>
            
        </div>
    </body>
</html>
