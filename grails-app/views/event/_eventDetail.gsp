<div class="transparent-box">
	<h2>${event?.name}</h2>
	<label>
		<g:message code="event.createdBy" default="Created By" />:
	</label>
	<modalbox:createLink controller="profile" action="detail" id="${event?.createdBy?.id}" title="${event?.createdBy?.userRealName}" width="500">
		${event?.createdBy?.userRealName}
	</modalbox:createLink>
	
	<br/>
	
	<g:if test="${event?.organizer}">
		<label><g:message code="event.organizedBy" default="Organized By" />:</label>
		<modalbox:createLink controller="organizer" action="detail" id="${event?.organizer?.id}" title="${event?.organizer?.name}" width="500">${event?.organizer?.name}</modalbox:createLink>
		<br/>
	</g:if>
	
	${event?.description}
	<table>
		<tbody>
            <tr class="prop">
                <td valign="top" class="name"><label><g:message code="event.startTime" default="Start Time" />:</label></td>

                <td valign="top" class="value"><util:dateFormat value="${event?.startTime}" format="dd-MMMM-yyyy" /></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><label><g:message code="event.endTime" default="End Time" />:</label></td>

                <td valign="top" class="value"><util:dateFormat value="${event?.endTime}" format="dd-MMMM-yyyy" /></td>

            </tr>

        </tbody>
	</table><br/>
	<maps:map geolocalization="${event?.location}"/>
</div>