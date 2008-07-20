import org.grails.plugins.springsecurity.service.AuthenticateService

/**
 * Person Controller.
 */
class UserController {

	AuthenticateService authenticateService

	// the delete, save and update actions only accept POST requests
	def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect(action: list, params: params)
	}

	def list = {
		if (!params.max) {
			params.max = 10
		}
		[personList: Person.list(params)]
	}

	def show = {
		[person: Person.get(params.id)]
	}

	/**
	 * Person delete action. Before removing an existing person,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def person = Person.get(params.id)
		if (person) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.username == person.username) {
				flash.message = "You can not delete yourself, please login as another admin and try again"
			}
			else {
				//first, delete this person from People_Authorities table.
				Authority.findAll().each { it.removeFromPeople(person) }
				person.delete()
				flash.message = "Person ${params.id} deleted."
			}
		}
		else {
			flash.message = "Person not found with id ${params.id}"
		}

		redirect(action: list)
	}

	def edit = {

		def person = Person.get(params.id)
		if (!person) {
			flash.message = "Person not found with id ${params.id}"
			redirect(action: list)
			return
		}

		[person: person, authorityList: Authority.list(params)]
	}

	/**
	 * Person update action.
	 */
	def update = {

		def person = Person.get(params.id)
		if (!person) {
			flash.message = "Person not found with id ${params.id}"
			redirect(action: edit, id: params.id)
			return
		}

		def oldPassword = person.passwd
		person.properties = params
		if (!params.passwd.equals(oldPassword)) {
			person.passwd = authenticateService.passwordEncoder(params.passwd)
		}
		if (person.save()) {
			Authority.findAll().each { it.removeFromPeople(person) }
			addRoles(person)
			redirect(action: show, id: person.id)
		}
		else {
			render(view: 'edit', model: [person: person])
		}
	}

	def create = {
		def person = new Person()
		person.properties = params
		[person: person, authorityList: Authority.list(params)]
	}

	/**
	 * Person save action.
	 */
	def save = {

		def person = new Person()
		person.properties = params
		person.passwd = authenticateService.passwordEncoder(params.passwd)
		if (person.save()) {
			addRoles(person)
			redirect(action: show, id: person.id)
		}
		else {
			render(view: 'create', model: [authorityList: Authority.list(params), person: person])
		}
	}

	private void addRoles(person) {
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				Authority.findByAuthority(key).addToPeople(person)
			}
		}
	}
}
