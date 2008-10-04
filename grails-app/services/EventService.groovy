class EventService {
	def authenticateService
	boolean transactional = true
	
	def create(event) {
		grantedModification(event)
		if(event.hasErrors()) {
			throw new RuntimeException("")
		}
		if(event.save()) {
			log.info "Guarde un evento"
		}
	}
	
	def grantedModification(event) {
		def user = authenticateService.userDomain()
		println user.authorities
	}
}
