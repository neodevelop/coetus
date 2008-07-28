<g:if test="${event != null}">
	<h2><g:message code="event.show" default="Show Event" /></h2>
	<br>
     <div class="dialog">
        <table>
            <tbody>


                <tr class="prop">
                    <td valign="top" class="name"><g:message code="event.name" default="Name" />:</td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'name')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="event.description" default="Description" />:</td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'description')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="event.startTime" default="Start Time" />:</td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'startTime')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="event.endTime" default="End Time" />:</td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'endTime')}</td>

                </tr>


                <tr class="prop">
                    <td valign="top" class="name"><g:message code="event.location" default="Location" />:</td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'location')}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="event.necessaryRegistry" default="Necessary Registry" />:</td>

                    <td valign="top" class="value">${fieldValue(bean:event, field:'necessaryRegistry')}</td>

                </tr>


				<g:if test="${event.showStatus}">
					<tr class="prop">
                        <td valign="top" class="name"><g:message code="event.status" default="Status" />:</td>

                        <td valign="top" class="value">${event?.status?.encodeAsHTML()}</td>

                    </tr>
				</g:if>

                

            </tbody>
        </table>
    </div>
	
    
</g:if>