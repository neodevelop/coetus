class Attendee {
	Person person
	Event event
	static hasMany = [talks: Talk]
}
