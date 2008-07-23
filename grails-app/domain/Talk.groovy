class Talk {
	Event event
	String title
	String summary
	Date startDate = new Date()
	Date endDate = new Date()
	String location
	Boolean necessaryRegistry = false
	
	static belongsTo = Person
	static hasMany = [speakers:Person]
	
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
		speakers column:'talk_Id',joinTable:'TALKS_SPEAKERS'
	}
}
