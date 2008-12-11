<resource:richTextEditor />
<resource:dateChooser />
<div class="box">
	<p>
		<label for="name"><g:message code="event.name" default="Name" />:</label><br/>
		<input type="text" maxlength="200" size="70" id="name" name="name" value="${event?.name}"/>
	</p>
	<p>
		<label for="description"><g:message code="event.description" default="Description" />:</label><br/>
		<richui:richTextEditor name="description" value="${event?.description}" width="425" />
	</p>
	<p>
		<label for="organizer"><g:message code="event.organizer" default="Organizer" />:</label><br/>
		<g:select optionKey="id" from="${Organizer.list()}" name="organizer.id" value="${event?.organizer?.id}" ></g:select>
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
	<util:selfUserOrAdmin person="${event?.createdBy}">
		<p>
			<label for="status"><g:message code="event.status" default="Status" />:</label><br/>
			<g:select  from="${Status?.values()}" value="${event?.status}" name="status" ></g:select>
		</p>
		<label>
			<modalbox:createLink controller="event" action="addTalkForm" id="${event?.id}" title="${event?.name}" width="500">
				<g:message code="event.addTalk" default="Add Talk" />
			</modalbox:createLink>
		</label>
	</util:selfUserOrAdmin>
</div>