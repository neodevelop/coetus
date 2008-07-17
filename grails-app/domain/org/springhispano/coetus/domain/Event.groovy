class Event {
	String name
	String description
	Date startTime
	Date endTime
	Boolean allDay = false
	String location
	Boolean necessaryRegistry = false
	
	static constraints = {
		name(blank:false,size:5..200)
		description(blank:false,size:5..2000)
		startTime(nullable:false)
		endTime(nullable:false)
		allDay(nullable:false)
		location(blank:false,size:5..200)
		necessaryRegistry(nullable:false)
	}
}
