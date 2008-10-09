class UtilTagLib {
	static namespace = "util"
	def authenticateService
	
	def checkUsers = { attrs ->
		checkDefaultRole()
		if(Person.count() == 0) {
			out << """<div class="message">"""
			out << 'Coetus no esta protegido, debes agregar a un usuario, hacerlo Administrador y proteger el sitio.'
			out << "</div>"
		}
	}
	
	def verifyUserManager = { attrs, body ->
		def event = attrs.event
		if(event?.createdBy?.id==authenticateService?.userDomain()?.id) {
			out << body()
		}
	}
	
	private void checkDefaultRole() {
		if(Authority.count() == 0) {
			new Authority(description:"Usuario del Sistema", authority:"ROLE_USER").save()
			new Authority(description:"Administrador del Sistema", authority:"ROLE_ADMIN").save()
			new Authority(description:"Orador", authority:"ROLE_SPEAKER").save()
			new Authority(description:"Administrador de eventos", authority:"ROLE_MANAGER").save()
			log.debug('Roles created')
		}
	}
	def managedCheckBox = { attrs ->
		def value = attrs.remove('value')
		def name = attrs.remove('name')
		def disabled = attrs.remove('disabled')
		if(!value) value = false
		out << """ <input type="hidden" """
		out << """name="_${name}" />"""
		out << """ <input type="checkbox" """
		out << """name="${name}" """
		if(value) {
			out << 'checked="checked" '
		}
		if(disabled != null && disabled == 'true') {
			out << 'disabled="disabled" '
		}
		out << """value="true" """
		// process remaining attributes
		//outputAttributes(attrs)
		// close the tag, with no body
		out << ' />'
	}
	def dateFormat = { attrs ->
	   out << new java.text.SimpleDateFormat(attrs.format).format(attrs.value)
	}
}
