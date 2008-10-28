class GeoLocalizationService {
	boolean transactional = false
	
	def map(location) {
		if(location && location.split(",").size()==2) {
			def l = location.split(",")
			return ["latitude":l[0], "longitude":l[1]]
		} else {
			log.warn "Location specified empty, return null"
			return null
		}
	}
}
