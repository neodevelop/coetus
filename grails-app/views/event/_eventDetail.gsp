<g:if test="${event != null}">
	<h2><g:message code="event.show" default="Show Event" /></h2>
	<div class="transparent-box">
		
	
	
		
        <table>
            <tbody>


                <tr class="prop">
                    <td valign="top" class="name"><label><g:message code="event.name" default="Name" />:</label></td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'name')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><label><g:message code="event.description" default="Description" />:</label></td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'description')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><label><g:message code="event.startTime" default="Start Time" />:</label></td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'startTime')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><label><g:message code="event.endTime" default="End Time" />:</label></td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'endTime')}</td>

                </tr>


                <tr class="prop">
                    <td valign="top" class="name"><label><g:message code="event.location" default="Location" />:</label></td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'location')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><label><g:message code="event.necessaryRegistry" default="Necessary Registry" />:</label></td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'necessaryRegistry')}</td>

                </tr>


				<g:if test="${event.showStatus}">
					<tr class="prop">
                        <td valign="top" class="name"><label><g:message code="event.status" default="Status" />:</label></td>

                        <td valign="top" class="value">${event?.status?.encodeAsHTML()}</td>

                    </tr>
				</g:if>

                

            </tbody>
        </table>
    </div>
	
    
</g:if>