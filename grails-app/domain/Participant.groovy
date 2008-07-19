class Participant {
	String name
	String company
	String email
	
	static belongsTo = [Talk]
	static hasMany = [talks:Talk]
}
