<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="person.show" default="Show Person" /></title>
    </head>
    <body>
		<!--
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="person.list" default="Person List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="person.new" default="New Person" /></g:link></span>
        </div>
		-->
        <div class="body">
            <g:render template="../person/personDetails"  model="['bean':person]" />
        </div>
    </body>
</html>
