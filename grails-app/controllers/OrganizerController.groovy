class OrganizerController {
	def scaffold = Organizer
	
	def choose = {
		def organizers = Organizer.count()
		if(organizers == 0) {
			render(template:"../organizer/emptyOrganizers")
		} else {
			render(template:"../organizer/chooseOrganizer",model:[organizers:Organizer.list()])
		}
	}
	def createOrganizer = {
		
	}
	def detail = {
		def organizer = Organizer.get(params.id)
		if(organizer) {
			render(template:"../organizer/organizerDetail",model:[organizer:organizer])
		}
	}
}