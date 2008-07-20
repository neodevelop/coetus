/*
 * Copyright 2007 the original author or authors.
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

/**
 * Shared methods/closures and initialization.
 *
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */

import groovy.text.SimpleTemplateEngine

grailsHome = Ant.project.properties.'environment.GRAILS_HOME'
includeTargets << new File("${grailsHome}/scripts/Init.groovy")

personDomainClassName = 'Person'
authorityDomainClassName = 'Authority'
requestmapDomainClassName = 'Requestmap'
templateDir = "${acegiPluginDir}/src/templates"
appDir = "${basedir}/grails-app"

overwrite = false

generateFile = { Map binding, String templatePath, String outputPath ->

	def templateFile = new File(templatePath)
	if (!templateFile.exists()) {
		println "${templatePath} doesn't exist"
		return
	}

	def outFile = new File(outputPath)
	if (outFile.exists() && !overwrite) {
	    println "file *not* generated: ${outFile.absolutePath}"
		return
	}

	outFile.withWriter { writer ->
		def template = new SimpleTemplateEngine().createTemplate(templateFile.text)
		template.make(binding).writeTo(writer)
	}

	println "file generated at ${outFile.absolutePath}"
}

copyFile = { String from, String to ->
	Ant.copy(file: from, tofile: to, overwrite: overwrite)
}

loadConfig = {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	Class clazz = loader.parseClass(new File("${basedir}/grails-app/conf/SecurityConfig.groovy"))
	def securityConfig = new ConfigSlurper().parse(clazz)
	personDomainClassName = securityConfig.security.loginUserDomainClass
	authorityDomainClassName = securityConfig.security.authorityDomainClass
	requestmapDomainClassName = securityConfig.security.requestMapClass
	println "Login user domain class: ${personDomainClassName}"
	println "Authority domain class: ${authorityDomainClassName}"
}
