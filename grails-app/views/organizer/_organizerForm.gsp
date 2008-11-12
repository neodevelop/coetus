<resource:richTextEditor />
<table>
    <tbody>
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="name"><g:message code="organizer.name" default="Name" />:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:organizer,field:'name','errors')}">
                <input type="text" size="42" maxlength="100" id="name" name="name" value="${fieldValue(bean:organizer, field:'name')}" />
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="website"><g:message code="organizer.website" default="Website" />:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:organizer,field:'website','errors')}">
				<input type="text" size="42" maxlength="100" id="website" name="website" value="${fieldValue(bean:organizer, field:'website')}" />
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="info"><g:message code="organizer.info" default="Info" />:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:organizer,field:'info','errors')}">
				<richui:richTextEditor name="info" value="${fieldValue(bean:organizer, field:'info')}" width="425" type="advanced" />
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="geolocation"><g:message code="organizer.geolocation" default="Geolocation" />:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:organizer,field:'geolocation','errors')}">
				<input type="text" size="42" maxlength="100" id="geolocation" name="geolocation" value="${fieldValue(bean:organizer, field:'geolocation')}" />
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="logoUrl"><g:message code="organizer.logoUrl" default="Logo Url" />:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:organizer,field:'logoUrl','errors')}">
				<input type="text" size="42" maxlength="300" id="logoUrl" name="logoUrl" value="${fieldValue(bean:organizer, field:'logoUrl')}" />
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="emailContact"><g:message code="organizer.emailContact" default="Email Contact" />:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:organizer,field:'emailContact','errors')}">
				<input type="text" size="42" maxlength="100" id="emailContact" name="emailContact" value="${fieldValue(bean:organizer, field:'emailContact')}" />
            </td>
        </tr> 
    
    
    </tbody>
</table>