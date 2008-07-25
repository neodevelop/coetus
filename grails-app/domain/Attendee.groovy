class Attendee {
	Person person
	Event event
	static hasMany = [talks: Talk]
	
	String toString() { "${person.username} - ${event.name}" }
}
