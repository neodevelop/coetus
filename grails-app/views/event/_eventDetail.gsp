<g:if test="${event != null}">
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
		<g:if test="${event?.hasLocation()}">
			<resource:googlemaps key="ABQIAAAAVI8tN8ZVK-n0fDPZbAvxyxSZfqRnYxVEt7pMH2xm7ibWPzQotxSFxMF4BAaFB9Wmu78Fr-tZELJp0g" />
			<richui:googlemaps lat="${event?.llatitude()}" lng="-${event?.llongitude()}" />
		</g:if>
		
	</div>
</g:if>