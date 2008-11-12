class FeedsController {
	def index = { redirect(uri:"/") }
	
	def allEvents = {
		render(feedType:"rss", feedVersion:"2.0") {
			title = "Coetus"
			link = "${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/feeds/allEvents"
			description = "All events in Coetus"
			Event.list().each() { event ->
				entry(event.name) {
					link = "${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/events/detail/${event.id}"
					if(event.dateCreated) { publishedDate = event.dateCreated }
					if(event.createdBy?.userRealName) { author = event.createdBy.userRealName }
					if(event.lastUpdated) { updatedDate = event.lastUpdated }
					title = event.name
					event.description
				}
			}
		}
	}
	
	def eventsByOrganizer = {
		def organizer = Organizer.get(params.id)
		if(organizer) {
			def criteria = Event.createCriteria()
			def events = criteria.list {
				eq("organizer.id", organizer.id)
			}
			render(feedType:"rss", feedVersion:"2.0") {
				title = "Coetus"
				link = "${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/feeds/eventsByOrganizer/${params.id}"
				description = "Eventos de ${organizer.name}"
				events.each() { event ->
					entry(event.name) {
						link = "${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/events/detail/${event.id}" 
						if(event.dateCreated) { publishedDate = event.dateCreated }
						if(event.createdBy?.userRealName) { author = event.createdBy.userRealName }
						if(event.lastUpdated) { updatedDate = event.lastUpdated }
						title = event.name
						event.description
					}
				}
			}
		} else {
			redirect(uri:"/")
		}
	}
}
