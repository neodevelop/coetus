<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="event.create" default="Create Event" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="event.list" default="Event List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="event.create" default="Create Event" /></h1>
            <g:render template="../util/showErrors" model="['bean':event]" />
            <g:form action="save" method="post" >
                <g:render template="../event/eventForm" model="['newRecord':true]"/>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
