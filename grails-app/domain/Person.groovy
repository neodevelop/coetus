/**
 * Person for user account.
 */
class Person {
	static transients = ['pass']
	static hasMany = [authorities: Authority]
	static belongsTo = Authority

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	boolean enabled = true

	String email
	boolean emailShow = false

	/** description */
	String description = ''
	
	String company
	String blog

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		username(blank: false, unique: true,size:1..30)
		userRealName(blank: false,size:1..200)
		company(nullable:true,blank:true,size:0..100)
		passwd(blank: false,size:0..300)
		email(blank:true,size:0..300,email:true)
		description(blank:true,size:0..1000)
		blog(nullable:true,blank:true,url:true,size:0..300)
		enabled()
	}
	
	static mapping = {
		tablePerHierarchy false
	}
}
