class EventsController {
	
	def index = { redirect(uri:"/") }

	def detail = {
		def event = Event.get(params.id)
		if (!event) {
			flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id {0}"
			return
		}
		[event: event]
	}
	
}
