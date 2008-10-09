<resource:richTextEditor />
<resource:dateChooser />
<div class="box">
	<p>
		<label for="name"><g:message code="event.name" default="Name" />:</label><br/>
		<input type="text" maxlength="200" id="name" name="name" value="${event?.name}"/>
	</p>
	<p>
		<label for="description"><g:message code="event.description" default="Description" />:</label><br/>
		<richui:richTextEditor name="description" value="${event?.description}" width="425" />
	</p>
	<p>
		<label for="startTime"><g:message code="event.startTime" default="Start Time" />:</label><br/>
		<richui:dateChooser name="startTime" format="MM/dd/yyyy" value="${event?.startTime}" />
	</p>
    
	<p>
		<label for="endTime"><g:message code="event.endTime" default="End Time" />:</label><br/>
		<richui:dateChooser name="endTime" format="MM/dd/yyyy" value="${event?.endTime}" />
	</p>
	<p>
		<label for="allDay"><g:message code="event.allDay" default="All Day" />:</label>
		<g:checkBox name="allDay" value="${event?.allDay}" ></g:checkBox>
	</p>
	<p>
		<label for="location"><g:message code="event.location" default="Location" />:</label><br/>
		<input type="text" maxlength="200" id="location" name="location" value="${event?.location}"/>
	</p>
	<p>
		<label for="necessaryRegistry"><g:message code="event.necessaryRegistry" default="Necessary Registry" />:</label>
		<g:checkBox name="necessaryRegistry" value="${event?.necessaryRegistry}" ></g:checkBox>
	</p>
	<p>
		<label for="showStatus"><g:message code="event.showStatus" default="Show Status" />:</label>
		<g:checkBox name="showStatus" value="${event?.showStatus}" ></g:checkBox>
	</p>
	<p>
		<label for="status"><g:message code="event.status" default="Status" />:</label><br/>
		<g:select  from="${Status?.values()}" value="${event?.status}" name="status" ></g:select>
	</p>
	<g:ifAllGranted role="ROLE_ADMIN">
		
	</g:ifAllGranted>
	<g:ifNotGranted role="ROLE_ADMIN">
		<g:ifAllGranted role="ROLE_MANAGER">
				<util:verifyUserManager event="${event}">
					<p>
						<label for="status"><g:message code="event.status" default="Status" />:</label><br/>
						<g:select  from="${Status?.values()}" value="${event?.status}" name="status" ></g:select>
					</p>
				</util:verifyUserManager>
		</g:ifAllGranted>
	</g:ifNotGranted>
	
	<p>
		<br/>
	</p>
	
</div>