/*
 * Copyright 2004-2005 the original author or authors.
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
 * Gant script that generates i18n messages for domain classes
 * 
 * @author Marcel Overdijk
 */

import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU
import org.codehaus.groovy.grails.commons.spring.GrailsRuntimeConfigurator;
import org.codehaus.groovy.grails.scaffolding.DomainClassPropertyComparator;
import org.springframework.mock.web.MockServletContext;

Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"    

includeTargets << new File ( "${grailsHome}/scripts/Package.groovy" )
includeTargets << new File ( "${grailsHome}/scripts/Bootstrap.groovy" )

target('default': "Generates i18n messages for domain classes") {
    doStuff()
}

target(doStuff: "The implementation task") {
    depends(checkVersion, configureProxy, packageApp, classpath, packagePlugins)

    if(!args) {
        Ant.input(addProperty:"artifact.name", message:"Domain class name not specified. Please enter:")
        args = Ant.antProject.properties."artifact.name"
    }  
    
    rootLoader.addURL(classesDir.toURL())
    loadApp()

    def name = args.trim()
    name = name.indexOf('.') > -1 ? name : GCU.getClassNameRepresentation(name)
    def domainClass = grailsApp.getDomainClass(name)

    if(!domainClass) {
        println "Domain class not found in grails-app/domain, trying hibernate mapped classes..."		
        try {
            def config = new GrailsRuntimeConfigurator(grailsApp, appCtx)  
            appCtx = config.configure(appCtx.servletContext)     			
        }   
        catch(Exception e) {
            println e.message
            e.printStackTrace()
        }
        domainClass = grailsApp.getDomainClass(name)  
    }

    if(domainClass) {
    
        // print generic messages for this domain class
        println "# ${domainClass.shortName} messages"
        println "${domainClass.propertyName}.create=Create ${domainClass.shortName}"
        println "${domainClass.propertyName}.edit=Edit ${domainClass.shortName}"
        println "${domainClass.propertyName}.list=${domainClass.shortName} List"
        println "${domainClass.propertyName}.new=New ${domainClass.shortName}"
        println "${domainClass.propertyName}.show=Show ${domainClass.shortName}"
        println "${domainClass.propertyName}.created=${domainClass.shortName} {0} created"
        println "${domainClass.propertyName}.updated=${domainClass.shortName} {0} updated"
        println "${domainClass.propertyName}.deleted=${domainClass.shortName} {0} deleted"
        println "${domainClass.propertyName}.not.found=${domainClass.shortName} not found with id {0}"

        // print messages for all properties contained by domain class
        props = domainClass.properties.findAll { it.name != 'version' }
        Collections.sort(props, new DomainClassPropertyComparator(domainClass))
        props.each { p ->
            println "${domainClass.propertyName}.${p.name}=${p.naturalName}"

            // print messages for inList constaint values
            cp = domainClass.constrainedProperties[p.name]
            if(cp?.inList) {
                cp.inList.each { v ->
                    println "${domainClass.propertyName}.${p.name}.${v}=${v}"
                }
            }
        }
        
        event("StatusFinal", ["Finished generation for domain class ${domainClass.fullName}. Copy messages to appropriate resource bundle(s)."])
    }                                                
    else {
        event("StatusFinal", ["No domain class found for name ${name}. Please try again and enter a valid domain class name"])		
    }
    
}