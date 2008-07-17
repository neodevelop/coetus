class Talk {
	Event event
	String title
	String summary
	Date startDate
	Date endDate
	String location
	Boolean necessaryRegistry = false
	
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
}
