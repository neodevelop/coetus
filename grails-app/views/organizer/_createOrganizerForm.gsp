<h1><g:message code="organizer.create" default="Create Organizer" /></h1>
<g:render template="../util/showErrors" model="['bean':organizer]" />
<g:form action="save" method="post" >
    <g:render template="../organizer/organizerForm" model="['newRecord':true]"/>
    <div class="buttons">
        <span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
    </div>
</g:form>