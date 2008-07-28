import org.springframework.security.context.SecurityContextHolder as SCH

class MyEventsController {
	
	def index = { redirect(uri:"/") }

    private boolean isAuthenticated() {
		def authPrincipal = SCH?.context?.authentication?.principal
		return authPrincipal != null && authPrincipal != 'anonymousUser'
	}
	def authenticateService

    def myDetail = {
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		
		def userName = SCH.context.authentication.principal.username
		def user = Person.findByUsername(userName)
		def attendees = Attendee.findByPersonAndEvent(user, event)
		def talksPendingToAttend = Talk.findAllByEvent(event)
		[event: event, attendee:attendees, talksPendingToAttend:talksPendingToAttend]
	}
	
	def register = {
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		[event: event]
	}
	
	def doRegistry ={
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		} else {
		}
		
		def cc = []
		
		params.each{ k, v-> 
			if(k.startsWith("talk_")) {
				def t = k.substring(k.lastIndexOf("_") + 1)
				cc.add(t)
			}
		}
				
		def tals = []
		
		def principal = authenticateService.principal()
		def attendee = new Attendee()
		//println attendee
		
		def person = Person.findByUsername(principal.getUsername())
		if(!person) {
			flash.message = "person.not.found"
            flash.args = [principal.getUserName]
            flash.defaultMessage = "Person not found with name {0}"
			return
		} else {
			println person
			println event
			attendee = new Attendee(person:person, event:event)
			attendee.save()
			println "Guarde el asistente"
			println attendee
		}
		
		cc.each() {
			def c = Talk.get(it)
			println c
			attendee.addToTalks(c)
			println "agregue la charla a la asistencia"
		}
		println attendee
		attendee.save()
		
		
		
	}
}
