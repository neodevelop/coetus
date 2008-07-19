class Talk {
	Event event
	String title
	String summary
	Date startDate = new Date()
	Date endDate = new Date()
	String location
	Boolean necessaryRegistry = false
	
	//static belongsTo = [Speaker, Participant]
	//static hasMany = [speakers:Speaker, participants:Participant]
	static belongsTo = Speaker
	static hasMany = [speakers:Speaker]
	
	static constraints = {
		event()
		title(blank:false,size:1..200)
		summary(blank:false,size:1..2000)
		startDate()
		endDate()
		location(blank:false,size:1..100)
		necessaryRegistry(nullable:false)
	}
	
	static mapping = {
		speakers column:'idTalk_speakers'
	}
}
