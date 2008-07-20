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
 * Generates view files and controller files for Spring Security user management.
 * @author Tsuyoshi Yamamoto
 * @author Haotian Sun
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */

includeTargets << new File("${acegiPluginDir}/scripts/SecurityTargets.groovy")

pluginTemplatePath = "${templateDir}/manager"

target('default': 'Generates view and controller for Spring Security user management') {
	loadConfig()
	
	generateControllerAndViews('User')
	generateControllerAndViews('Role')
	generateControllerAndViews('Requestmap')
}

private void generateControllerAndViews(String name) {

	String path = "${basedir}/grails-app/controllers/${name}Controller.groovy"
	def outFile = new File("${path}")
	if (outFile.exists()) {
		Ant.input(addProperty: 'overwrite', message: "${outFile.name} exists - overwrite? y/n")
		if ('y' == Ant.antProject.properties.'overwrite') {
			overwrite = true
		}
	}
	else {
		overwrite = true
	}

	println "generating files for ${name} ......."
	def binding = [personDomain: personDomainClassName,
	               authorityDomain: authorityDomainClassName,
	               requestmapDomain: requestmapDomainClassName]

	//generate UserController.groovy
	println "generating file ${path}"
	generateFile(binding,
		"${pluginTemplatePath}/controllers/_${name}Controller.groovy",
		"${path}")

	//generate views for UserController
	path = "${basedir}/grails-app/views/${name.toLowerCase()}"
	String viewPath = "${pluginTemplatePath}/views/${name.toLowerCase()}"
	println "generating view files - ${path}/* "
	Ant.mkdir(dir: "${path}")
	generateFile(binding, "${viewPath}/list.gsp", "${path}/list.gsp")
	generateFile(binding, "${viewPath}/edit.gsp", "${path}/edit.gsp")
	generateFile(binding, "${viewPath}/create.gsp", "${path}/create.gsp")
	generateFile(binding, "${viewPath}/show.gsp", "${path}/show.gsp")
}
