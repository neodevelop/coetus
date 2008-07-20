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
 * Create/Copy Domains, auth.gsp, Controllers for security plugin.
 *
 * @author Tsuyoshi Yamamoto
 * @author <a href='mailto:beckwithb@studentsonly.com'>Burt Beckwith</a>
 */
 
includeTargets << new File("${acegiPluginDir}/scripts/SecurityTargets.groovy")

target('default': 'Creates Domain classes for Spring Security plugin') {
	parseArgs()
	createDomains()
	copyViewAndControllers()
}

private void parseArgs() {
	args = args ? args.split('\n') : []
	switch (args.size()) {
		case 0:
			println 'Create domain classes with default name'
			return
		case 1:
			personDomainClassName = args[0]
			println "Login user domain class: ${personDomainClassName}"
			//prompt for authority name
			Ant.input(addProperty: 'authority.name', message: 'authority domain class name not specified. Please enter:')
			def authorityClassNames = Ant.antProject.properties.'authority.name'
			if (authorityClassNames) {
				def splitNames = authorityClassNames.split('\n')
				if (splitNames.size() == 1) {
					authorityDomainClassName = splitNames[0]
					println "Authority domain class: ${authorityDomainClassName}"
					return
				}
			}
			break
		case 2:
			personDomainClassName = args[0]
			authorityDomainClassName = args[1]
			println "Login user domain class: ${personDomainClassName}"
			println "Authority domain class: ${authorityDomainClassName}"
			return
	}

	usage()
}

private void usage() {
	println 'usage: grails create-auth-domains person authority'
	System.exit(1)
}

private void createDomains() {

	def binding = [personDomain: personDomainClassName,
	               authorityDomain: authorityDomainClassName,
	               requestmapDomain: requestmapDomainClassName]

	//create Person domain class
	generateFile(binding,
			"${templateDir}/_Person.groovy",
			"${appDir}/domain/${personDomainClassName}.groovy")

	//create Authority domain class
	generateFile(binding,
			"${templateDir}/_Authority.groovy",
			"${appDir}/domain/${authorityDomainClassName}.groovy")

	//copy Requestmap domain class
	println 'copying Requestmap domain class.'
	copyFile "${templateDir}/_Requestmap.groovy", "${appDir}/domain/Requestmap.groovy"

	//create SecurityConfig
	generateFile(binding,
			"${templateDir}/_SecurityConfig.groovy",
			"${appDir}/conf/SecurityConfig.groovy")
}

private void copyViewAndControllers() {

	//copy login.gsp and Login/Logout Controller example.
	println 'copying login.gsp and Login/Logout Controller example. '
	Ant.mkdir(dir: "${appDir}/views/login")
	copyFile "${templateDir}/views/login/auth.gsp",
		"${appDir}/views/login/auth.gsp"
	copyFile "${templateDir}/views/login/openIdAuth.gsp",
		"${appDir}/views/login/openIdAuth.gsp"
	copyFile "${templateDir}/controllers/LoginController.groovy",
		"${appDir}/controllers/LoginController.groovy"
	copyFile "${templateDir}/controllers/LogoutController.groovy",
		"${appDir}/controllers/LogoutController.groovy"

	//log4j.logger.org.springframework.security='off,stdout'
	def configFile = new File("${appDir}/conf/Config.groovy")
	if (configFile.exists()) {
		configFile.append("\n\n//log4j.logger.org.springframework.security='off,stdout'")
	}
}
