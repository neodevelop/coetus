/**
 * ${authorityDomain} class for Authority.
 */
class ${authorityDomain} {

	static hasMany = [people: ${personDomain}]

	/** description */
	String description
	/** ROLE String */
	String authority = 'ROLE_'

	static constraints = {
		authority(blank: false)
		description()
	}
}
