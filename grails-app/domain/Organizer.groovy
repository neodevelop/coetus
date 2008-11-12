class Organizer {
	static hasMany = [people: Person]
	
	String name
	String website
	String info
	String geolocation
	String logoUrl
	String emailContact
	Person createdBy
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank:false,size:0..100)
		website(nullable:true,blank:true,url:true,size:0..100)
		info(blank:true,size:0..15000)
		geolocation(blank:true,size:0..1000,geolocation:true)
		logoUrl(nullable:true,blank:true,url:true,size:0..300)
		emailContact(nullable:true,blank:true,email:true,size:0..100)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		createdBy(nullable:true)
	}
	
	String toString() { name }
}
