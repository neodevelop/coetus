import org.springframework.security.context.SecurityContextHolder as SCH
import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

class EventTagLib {
	
	private boolean isAuthenticated() {
		def authPrincipal = SCH?.context?.authentication?.principal
		return authPrincipal != null && authPrincipal != 'anonymousUser'
	}
	
	def currentEvents = { attrs ->
		out <<  '<h3>'
		out <<  attrs.title
		out <<  '</h3> <div class="content"> <ul class="linklist">'
		
		for(event in Event.findAllByStatus(Status.OPEN)) {
			out << '<li> <a href="'
			out << request.contextPath
			out << '/events/detail/'
			out << event.id
			out << '">'
			out << event.name
			out << '</a> </li>'
		}
		out << '</ul> </div>'
	}
	
	def myEvents = { attrs ->
		if (isAuthenticated()) {
			out <<  '<h3>'
			out <<  attrs.title
			out <<  '</h3> <div class="content"> <ul class="linklist">'
			
			def userName = SCH.context.authentication.principal.username
			def user = Person.findByUsername(userName)
			def attendees = Attendee.findByPerson(user)
			
			if(!attendees) {
				out << 'Sin eventos'
			} else {
				for(attendee in attendees) {
					out << '<li> <a href="'
					out << request.contextPath
					out << '/myEvents/myDetail/'
					out << attendee.event.id
					out << '">'
					out << attendee.event.name
					out << '</a> </li>'
				}
			}
			out << '</ul> </div>'
		}
	}	
}
