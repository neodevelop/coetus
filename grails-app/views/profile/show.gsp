<html>
	<head>
        <meta name="layout" content="main" />
        <title><g:message code="person.show" default="Show Person" /></title>
    </head>
    <body>
        <div class="body">
			<resource:tabView />
			<richui:tabView id="tabView">
			    <richui:tabLabels>
			        <richui:tabLabel selected="true" title="${message(code:'person.show', default:'Show Person')}" />
					<richui:tabLabel title="${message(code:'person.geolocalization',default:'Geolocalization')}" />
					<richui:tabLabel title="${message(code:'events',default:'Events')}" />
			    </richui:tabLabels>

			    <richui:tabContents>
			        <richui:tabContent>
				    <g:render template="../person/personDetails"  model="['bean':person]" />
					<util:selfUserOrAdmin person="${person}">
						<div class="buttons">
							<span class="menuButton"><g:link class="create" action="edit"><g:message code="edit" default="Edit" /></g:link></span>
						</div>
					</util:selfUserOrAdmin>
				</richui:tabContent>
				<richui:tabContent>
				    Implement this
				</richui:tabContent>
				<richui:tabContent>
				<g:message code="events.current", default="Current Events" />
		        <g:message code="events.past", default="Past Events" />
				    Implement this, please see: 
				http://code.google.com/p/coetus/issues/detail?id=31
				http://code.google.com/p/coetus/issues/detail?id=32
				</richui:tabContent>
			    </richui:tabContents>
			</richui:tabView>
        </div>
    </body>
</html>
