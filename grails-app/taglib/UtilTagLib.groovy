/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	
	def selfUserOrAdmin = { attrs, body ->
		def idUsr = attrs.person?.id
		if("true".equals(ifAllGranted(role:"ROLE_ADMIN") {'true'}) || (authenticateService.userDomain()?.id == idUsr)) {
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
		out << ' />'
	}
	def dateFormat = { attrs ->
		if(attrs.value && attrs.value instanceof Date) {
			out << new java.text.SimpleDateFormat(attrs.format).format(attrs.value)
		} else {
			log.warn "the value is not a java.util.Date"
		}
	}
	
	def modalIncludes = {
        def jsFolder    = createLinkTo(dir:'plugins',file:'modalbox-0.3/js/modalbox')
        def cssFolder   = createLinkTo(dir:'plugins',file:'modalbox-0.3/css')
        
        out << """
    		<script type='text/javascript' src='${jsFolder}/modalbox.js'></script>
    		<link rel='stylesheet' href='${cssFolder}/modalbox.css' />
        """
    }
}
