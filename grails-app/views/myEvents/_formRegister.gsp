<g:form method="post" >
	<div class="buttons">
		<input type="hidden" name="id" value="${event?.id}" />
        <span class="button"><g:actionSubmit class="save" action="doRegistry" value="${message(code:'event.register.confirm', 'default':'Confirm event register')}" /></span>
    </div>	
</g:form>