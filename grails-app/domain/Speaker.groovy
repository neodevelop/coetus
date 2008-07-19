class Speaker {
	String name
	String bio
	String blog
	String email
	String company
	Boolean publicEmail = false
	
	static belongsTo = [Talk]
	static hasMany = [talks:Talk]
	
	static constraints = {
		name(blank:false,size:1..100)
		bio(blank:false,size:1..3000)
		blog(blank:true,url:true)
		email(blank:false,email:true)
		company(blank:true,size:1..100)
		publicEmail()
	}
}
