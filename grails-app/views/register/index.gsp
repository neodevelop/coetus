<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="person.create" default="Create Person" /></title>
	</head>
	<body>
		<h1><g:message code="person.create" default="Create Person" /></h1>
		<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
			<div class="errors">
				<g:renderErrors bean="${person}" as="list" />
			</div>
		</g:hasErrors>
		<g:form action="save" method="post" name='registerForm' >
        	<div class="box">
				<p>
					<label for="username"><g:message code="person.username" default="Username" />:</label>:<br/>
					<input type="text" size="42" maxlength="30" id="username" name="username" value="${fieldValue(bean:person,field:'username')}"/>
				</p>
				<p>
					<label for="userRealName"><g:message code="person.userRealName" default="User Real Name" />:</label><br/>
					<input type="text" size="42" maxlength="200" id="userRealName" name="userRealName" value="${fieldValue(bean:person,field:'userRealName')}"/>
				</p>
				<p>
					<label for="passwd"><g:message code="person.passwd" default="Passwd" />:</label><br/>
					<input type="password" name='passwd' value="" size="42" />
				</p>
				<p>
					<label for="passwd"><g:message code="person.confirmPassword" default="Confirm Password" />:</label><br/>
					<input type="password" name='repasswd' value="" size="42" />
				</p>
				<p>
					<label for="email"><g:message code="person.email" default="Email" />:</label><br/>
					<input type="text" size="42" maxlength="100" id="email" name="email" value="${fieldValue(bean:person,field:'email')}"/>
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
                    <label for="canBeContactedViaEmail">Show my email:</label>
					<g:checkBox name="canBeContactedViaEmail" id="canBeContactedViaEmail" value="${fieldValue(bean:person,field:'emailShow')}" />
                </p>
				<p>
                    <label for="canBeContactedViaEmail">Allow people to contact me via email:</label>
					<g:checkBox name="canBeContactedViaEmail" id="canBeContactedViaEmail" value="${fieldValue(bean:person,field:'canBeContactedViaEmail')}" />
                </p>
				<p>
					<label for='code'><g:message code="person.enterCode" default="Enter Code" />: </label><br/>
					<input type="text" name="captcha" size="8" size="42" />
                  	<img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/>
				</p>
			</div>
			<div class="buttons">
				<span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
			</div>
			<script type='text/javascript'>
				<!--
				(function(){
					document.forms['registerForm'].elements['username'].focus();
				})();
				// -->
			</script>
		</g:form>
	</body>
</html>
