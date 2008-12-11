<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="talk.create" default="Create Talk" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="talk.list" default="Talk List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="talk.create" default="Create Talk" /></h1>
            <g:render template="../util/showErrors" model="['bean':talk]" />
            <g:form action="save" method="post" >
                <g:render template="talkForm" />
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code:'create', 'default':'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
