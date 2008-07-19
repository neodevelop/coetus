class Talk {
	Event event
	String title
	String summary
	Date startDate = new Date()
	Date endDate = new Date()
	String location
	Boolean necessaryRegistry = false
	
	static hasMany = [speakers:Speaker, participants:Participant]
	//static hasMany = [ participants : Participant ]
	
	static constraints = {
		event()
		title(blank:false,size:1..200)
		summary(blank:false,size:1..2000)
		startDate()
		endDate()
		location(blank:false,size:1..100)
		necessaryRegistry(nullable:false)
	}
}
