<div class="transparent-box">
	<h2>${event?.name}</h2>
	<label><g:message code="event.createdBy" default="Created By" />:</label>${event?.createdBy?.userRealName}<br/>
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