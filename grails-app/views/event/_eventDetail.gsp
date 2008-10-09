<g:if test="${event != null}">
	<div class="transparent-box">
		<table>
	        <tbody>
	        	<tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.createdBy" default="Created By" />:</label></td>

	                <td valign="top" class="value">${event?.createdBy}</td>

	            </tr>

	            <tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.name" default="Name" />:</label></td>

	                <td valign="top" class="value">${event?.name}</td>

	            </tr>

	            <tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.description" default="Description" />:</label></td>

	                <td valign="top" class="value">${event?.description}</td>

	            </tr>

	            <tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.startTime" default="Start Time" />:</label></td>

	                <td valign="top" class="value"><util:dateFormat value="${event?.startTime}" format="dd-MMMM-yyyy" /></td>

	            </tr>

	            <tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.endTime" default="End Time" />:</label></td>

	                <td valign="top" class="value"><util:dateFormat value="${event?.endTime}" format="dd-MMMM-yyyy" /></td>

	            </tr>

	            <tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.allDay" default="All Day" />:</label></td>

	                <td valign="top" class="value"><util:managedCheckBox disabled="true" name="showStatus" value="${event?.allDay}" /> </td>

	            </tr>

	            <tr class="prop">
	                <td valign="top" class="name"><label><g:message code="event.necessaryRegistry" default="Necessary Registry" />:</label></td>

	                <td valign="top" class="value"><util:managedCheckBox disabled="true" name="showStatus" value="${event?.necessaryRegistry}" /></td>

	            </tr>
	
				<g:if test="${event.showStatus}">
					<tr class="prop">
		                <td valign="top" class="name"><label><g:message code="event.status" default="Status" />:</label></td>

		                <td valign="top" class="value">${event?.status?.encodeAsHTML()}</td>

		            </tr>
				</g:if>
	            

	        </tbody>
	    </table><br/>
		<resource:googlemaps key="ABQIAAAAVI8tN8ZVK-n0fDPZbAvxyxSZfqRnYxVEt7pMH2xm7ibWPzQotxSFxMF4BAaFB9Wmu78Fr-tZELJp0g" />
		<richui:googlemaps lat="${event?.llatitude()}" lng="-${event?.llongitude()}" />
	</div>
</g:if>