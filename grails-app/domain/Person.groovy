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
		company(blank:true,size:1..100)
		passwd(blank: false,size:1..30)
		email(blank: false,size:1..300,email:true)
		description(blank:true,size:1..1000)
		blog(blank:true,url:true,size:1..300)
		enabled()
	}
	
	static mapping = {
		tablePerHierarchy false
	}
}
