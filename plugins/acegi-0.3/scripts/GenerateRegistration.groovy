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
 * Generates user registration views and controllers.
 *
 * @author Haotian Sun
 * @author Tsuyoshi Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */

includeTargets << new File("${acegiPluginDir}/scripts/SecurityTargets.groovy")

pluginTemplatePath = "${templateDir}/manager"

target('default': 'Generates user registration views and controllers') {

	loadConfig()

	if (!new File("${basedir}/lib/mail-1.4.jar").exists()) {
		println "Downloading mail-1.4 ..."
		get(dest: "${basedir}/lib/mail-1.4.jar",
			src: "http://repo1.maven.org/maven2/javax/mail/mail/1.4/mail-1.4.jar",
			verbose: true,
			usetimestamp: true)
	}

	if (!new File("${basedir}/lib/activation-1.1.jar").exists()) {
		println "Downloading activation-1.1.jar ..."
		get(dest: "${basedir}/lib/activation-1.1.jar",
			src: "http://repo1.maven.org/maven2/javax/activation/activation/1.1/activation-1.1.jar",
			verbose: true,
			usetimestamp: true)
	}

	generateRegistration 'register'
}

private void generateRegistration(String name) {

	def uname = name[0].toUpperCase() + name.substring(1)
	def outFile = new File("${basedir}/grails-app/controllers/${uname}Controller.groovy")
	if (outFile.exists()) {
		Ant.input(addProperty: 'overwrite', message: 'Do you want to overwrite? y/n')
		if ('y' == Ant.antProject.properties.'overwrite') {
			overwrite = true
		}
	}
	else {
		overwrite = true
	}

	println "generating files for ${uname} ......."
	def binding = [personDomain: personDomainClassName,
	               authorityDomain: authorityDomainClassName,
	               requestmapDomain: requestmapDomainClassName]

	//copy the CaptchaController
	String dest = "${basedir}/grails-app/controllers/CaptchaController.groovy"
	println "copying CaptchaController.groovy to - ${dest}"
	copyFile "${pluginTemplatePath}/controllers/_CaptchaController.groovy", "${dest}"

	//copy the EmailerService
	dest = "${basedir}/grails-app/services/EmailerService.groovy"
	println "copying EmailerService.groovy to - ${dest}"
	copyFile "${pluginTemplatePath}/services/_EmailerService.groovy", "${dest}"

	//generate RegisterController.groovy
	dest = "${basedir}/grails-app/controllers/${uname}Controller.groovy"
	println "generating file ${dest}"
	generateFile(binding,
			"${pluginTemplatePath}/controllers/_${uname}Controller.groovy",
			"${dest}")

	//generate views for RegisterController
	dest = "${basedir}/grails-app/views/${name}"
	println "copying view files to - ${dest}/*"
	Ant.mkdir(dir: "${dest}")
	copyFile "${pluginTemplatePath}/views/${name}/edit.gsp", "${dest}/edit.gsp"
	copyFile "${pluginTemplatePath}/views/${name}/index.gsp", "${dest}/index.gsp"
	copyFile "${pluginTemplatePath}/views/${name}/show.gsp", "${dest}/show.gsp"
}
