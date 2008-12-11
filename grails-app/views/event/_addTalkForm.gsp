

<g:form url="[action:'addTalkToEvent',controller:'event']" id="${event.id}">
	<input type="hidden" id="eventId" name="eventId" value="${event?.id}" />
    <g:render template="../talk/talkForm" model="['event':event]"/>

    <g:actionSubmit value="addTalkToEvent" onclick="return confirm('Are you sure???')" />
</g:form>