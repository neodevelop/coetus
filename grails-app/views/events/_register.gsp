<g:if test="${event.status == Status.OPEN}">
	<div class="buttons">
        <g:form>
            <input type="hidden" name="id" value="${event?.id}" />
			<g:link controller="myEvents" action="register" id="${event.id}">
				<span>
					<g:message code="event.register" default="Register" />
				</span>
			</g:link>
        </g:form>
    </div>
</g:if>