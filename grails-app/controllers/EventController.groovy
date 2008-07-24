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
			/*def principal = authenticateService.principal()
			
			def person = Person.findByUsername(principal.getUsername())
			if(!person) {
				flash.message = "person.not.found"
	            flash.args = [principal.getUserName]
	            flash.defaultMessage = "Person not found with name {0}"
			} else {
				def attendee = new Attendee(person:person, event:event)
				attendee.save()
			}*/
		}
		[event: event]
	}
	
	def doRegistry ={
		println params
		
		params.each{ k, v-> 
			println k 
			println v
		}
		
		
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
		} else {
			
		}
	}
}