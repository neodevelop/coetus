/**
 * Authority class for Authority.
 */
class Authority {

	static hasMany = [people: Person]

	/** description */
	String description
	/** ROLE String */
	String authority = 'ROLE_'

	static constraints = {
		authority(blank: false)
		description()
	}
}
