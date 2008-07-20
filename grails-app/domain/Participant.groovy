class Participant {
	String name
	String company
	String email
	
	static hasMany = [talks:Talk]
}
