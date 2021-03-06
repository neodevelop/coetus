<html>
  <head>
    <meta name="layout" content="main" />
    <title>User Profile</title>
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a href="${createLinkTo(dir:'')}">Home</a></span>
    </div>
    
    <div class="body">
      <h1>User Profile</h1>
      <div class="dialog">
        <table>
          <tbody>

            <tr class="prop">
              <td valign="top" class="name">Login Name:</td>
              <td valign="top" class="value">
              	${person.username?.encodeAsHTML()}
              </td>
            </tr>
                   
            <tr class="prop">
              <td valign="top" class="name">Full Name:</td>
              <td valign="top" class="value">
                ${person.userRealName?.encodeAsHTML()}
              </td>
            </tr>
                   
            <tr class="prop">
              <td valign="top" class="name">Enabled:</td>
              <td valign="top" class="value">
                ${person.enabled?.encodeAsHTML()}
              </td>
            </tr>

            <tr class="prop">
              <td valign="top" class="name">Email:</td>
              <td valign="top" class="value">
                ${person.email?.encodeAsHTML()}
              </td>
            </tr>
                   
            <tr class="prop">
              <td valign="top" class="name">Show Email:</td>
              <td valign="top" class="value">
                ${person.emailShow?.encodeAsHTML()}
              </td>
            </tr>
                   
            <tr class="prop">
              <td valign="top" class="name">Roles:</td>
              <td valign="top" class="value">
                <ul>
                <g:collect in="${person.authorities}" expr="${it.authority}">
                  <li>${it?.substring(5)?.toLowerCase()}</li>
                </g:collect>
                </ul>
              </td>
            </tr>
                   
          </tbody>
        </table>
      </div>
      
      <div class="buttons">
        <g:form controller="register">
          <input type="hidden" name="id" value="${person?.id}" />
          <span class="button"><g:actionSubmit value="Edit" /></span>
        </g:form>
      </div>
      
    </div>
  </body>
</html>
