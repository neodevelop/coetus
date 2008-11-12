<div class="transparent-box">
	<h2>${organizer?.name}</h2>
	<label><g:message code="organizer.website" default="Website" />:</label>${fieldValue(bean:organizer, field:'website')}<br/>
	

	${fieldValue(bean:organizer, field:'info')}
	
	<br />
	
	<g:link controller="feeds" action="eventsByOrganizer" id="${organizer?.id}">Eventos de ${organizer?.name}</g:link>
	
	
<!--
<tr class="prop">
    <td valign="top" class="name"><g:message code="organizer.geolocation" default="Geolocation" />:</td>
    
    <td valign="top" class="value">${fieldValue(bean:organizer, field:'geolocation')}</td>
    
</tr>


<tr class="prop">
    <td valign="top" class="name"><g:message code="organizer.logoUrl" default="Logo Url" />:</td>
    
    <td valign="top" class="value">${fieldValue(bean:organizer, field:'logoUrl')}</td>
    
</tr>


<tr class="prop">
    <td valign="top" class="name"><g:message code="organizer.emailContact" default="Email Contact" />:</td>
    
    <td valign="top" class="value">${fieldValue(bean:organizer, field:'emailContact')}</td>
    
</tr>
-->


</div>