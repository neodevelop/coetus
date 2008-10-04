class CreateController {
	def eventService
	def authenticateService
	def index = {redirect(action:event)}
	def event = {
		[event:new Event()]
	}
	def saveEvent = {
		def event = new Event()
		event.properties = params
		event.createdBy = authenticateService.userDomain()
		eventService.create event
		redirect(controller:"event", action:"show", id:event.id)
	}
}
