<resource:richTextEditor />
<div class="box">
	<p>
		<label for="username"><g:message code="person.username" default="Username" />:</label>:<br/>
		<input type="hidden" id="id" name="id" value="${person?.id}" />
		<input type="text" size="42" maxlength="30" id="username" name="username" value="${person?.username}"/>
	</p>
	<p>
		<label for="userRealName"><g:message code="person.userRealName" default="User Real Name" />:</label><br/>
		<input type="text" size="42" maxlength="200" id="userRealName" name="userRealName" value="${person?.userRealName}"/>
	</p>
	<p>
		<p><label for="description"><g:message code="person.description" default="Description" />:</label><br />
		<richui:richTextEditor name="description" value="${person?.description}" width="425" type="advanced" />
	</p>
	<g:if test="${newRecord}">
		<g:render template="../person/personPasswordForm" />
	</g:if>
	<p>
		<label for="email"><g:message code="person.email" default="Email" />:</label><br/>
		<input type="text" size="42" maxlength="100" id="email" name="email" value="${fieldValue(bean:person,field:'email')}"/><br/>
		<span class="meta-info">Associate this email address with an avatar for free at <a href="http://gravatar.com/" target="_blank">gravatar.com</a></span>
	</p>
	<p>
		<label for="company"><g:message code="person.company" default="Company" />:</label><br/>
		<input type="text" size="42" maxlength="100" id="company" name="company" value="${fieldValue(bean:person,field:'company')}"/>
	</p>
	<p>
		<label for="blog"><g:message code="person.blog" default="Blog" />:</label><br/>
		<input type="text" size="42" maxlength="100" id="blog" name="blog" value="${fieldValue(bean:person,field:'blog')}"/>
	</p>
	<p>
        <label for="canBeContactedViaEmail"><g:message code="person.canBeContactedViaEmail" default="Allow people to contact me via email" />:</label>
		<g:checkBox name="canBeContactedViaEmail" id="canBeContactedViaEmail" value="${fieldValue(bean:person,field:'canBeContactedViaEmail')}" />
    </p>
	<g:if test="${newRecord}">
		<p>
	        <label for="manager"><g:message code="person.manager" default="Do you wish register as Event Manager?" />:</label>
			<g:checkBox name="manager" id="manager" />
	    </p>
	</g:if>
	<p>
        <label for="geolocation"><g:message code="person.geolocation" default="Geo Location" />:</label><br/>
		<input type="text" size="42" maxlength="100" id="geolocation" name="geolocation" value="${fieldValue(bean:person,field:'geolocation')}"/>
    </p>
	<g:ifAllGranted role="ROLE_ADMIN">
		<p><label for="enabled"><g:message code="person.enabled" default="Enabled" />:</label>
		<g:checkBox name="enabled" value="${person?.enabled}" ></g:checkBox></p>
		<ul>
		<g:each var="a" in="${person?.authorities?}">
		    <li><g:link controller="authority" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
		</g:each>
		</ul>
		<g:link controller="authority" params="['person.id':person?.id]" action="create">Add Authority</g:link>
		
	</g:ifAllGranted>
	<g:if test="${newRecord}">
		<p>
			<g:if test="${recaptcha_error}">
				<div class="message">${recaptcha_error}</div>
			</g:if>

			 <recaptcha:ifEnabled> <recaptcha:recaptcha /> </recaptcha:ifEnabled>
		</p>
	</g:if>
</div>
