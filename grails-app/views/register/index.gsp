
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title><g:message code="person.create" default="Create Person" /></title>         
  </head>
  <body>
    <div class="body">
      <h1><g:message code="person.create" default="Create Person" /></h1>
		<br>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${person}">
        <div class="errors">
          <g:renderErrors bean="${person}" as="list" />
        </div>
      </g:hasErrors>
      <g:form action="save" method="post" >
        <div class="dialog">
          <table>
            <tbody>
			
				<tr class="prop">
                  <td valign="top" class="name">
                      <label for="username"><g:message code="person.username" default="Username" />:</label>
                  </td>
                  <td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
                      <input type="text" maxlength="30" id="username" name="username" value="${fieldValue(bean:person,field:'username')}"/>
                  </td>
              </tr>
			
              	<tr class="prop">
                  <td valign="top" class="name">
                      <label for="userRealName"><g:message code="person.userRealName" default="User Real Name" />:</label>
                  </td>
                  <td valign="top" class="value ${hasErrors(bean:person,field:'userRealName','errors')}">
                      <input type="text" maxlength="200" id="userRealName" name="userRealName" value="${fieldValue(bean:person,field:'userRealName')}"/>
                  </td>
              </tr>
                       
              	<tr class="prop">
                  <td valign="top" class="name">
                      <label for="passwd"><g:message code="person.passwd" default="Passwd" />:</label>
                  </td>
                  <td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
					  <input type="password" name='passwd' value=""/>
                  </td>
              </tr>

			<tr class="prop">
                <td valign="top" class="name">
                    <label for="passwd"><g:message code="person.confirmPassword" default="Confirm Password" />:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
					  <input type="password" name='repasswd' value=""/>
                </td>
            </tr>

			<tr class="prop">
                <td valign="top" class="name">
                    <label for="email"><g:message code="person.email" default="Email" />:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:person,field:'email','errors')}">
                    <input type="text" maxlength="100" id="email" name="email" value="${fieldValue(bean:person,field:'email')}"/>
                </td>
            </tr>

			<tr class="prop">
                <td valign="top" class="name">
                    <label for="company"><g:message code="person.company" default="Company" />:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:person,field:'company','errors')}">
                    <input type="text" maxlength="100" id="company" name="company" value="${fieldValue(bean:person,field:'company')}"/>
                </td>
            </tr>

			<tr class="prop">
                <td valign="top" class="name">
                    <label for="blog"><g:message code="person.blog" default="Blog" />:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:person,field:'blog','errors')}">
                    <input type="text" maxlength="100" id="blog" name="blog" value="${fieldValue(bean:person,field:'blog')}"/>
                </td>
            </tr>

              <tr class='prop'>
                <td valign='bottom' class='name'>
                  <label for='code'><g:message code="person.enterCode" default="Enter Code" />: </label>
                </td>
                <td valign='top' class='name'>
                  <input type="text" name="captcha" size="8"/>
                  <img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/>
                </td>
              </tr>

            </tbody>
          </table>
        </div>
        
        <div class="buttons">
			<span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
        </div>
            
      </g:form>
    </div>
  </body>
</html>
