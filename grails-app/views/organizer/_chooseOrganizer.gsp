<p>
    <label for="organizer"><g:message code="organizer.name" default="Organization" />:</label> <br/>
	<g:select optionKey="id" from="${organizers}" name="organizer.id" value="${organizer?.id}" ></g:select>
</p>