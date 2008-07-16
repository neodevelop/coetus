class Event {
	String subject
	EventType type
	Date startTime
	Date endTime
	Boolean allDay = false
	String location
	Boolean necessaryRegistry = false
	
	static constraints = {
		subject(blank:false,size:5..200)
		type(nullable:false)
		startTime(nullable:false)
		endTime(nullable:false)
		allDay(nullable:false)
		location(blank:false,size:5..200)
		necessaryRegistry(nullable:false)
	}
}
