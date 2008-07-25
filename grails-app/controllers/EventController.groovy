class EventController {
	def authenticateService
	
    def scaffold = true

	def detail = {
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
		}
		[event: event]
	}
	
	def register = {
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
		} else {
			/* */
		}
		[event: event]
	}
	
	def doRegistry ={
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
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