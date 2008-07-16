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
 * Installs the i18n scaffolding templates
 *
 * @author Marcel Overdijk
 */

Ant.copy(todir: "${basedir}/src/templates/scaffolding", overwrite: true) {
    fileset(dir: "${pluginBasedir}/src/grails/templates/scaffolding")
}

// Workaround for GRAILS-1668: Resource bundles contained by plugins are not resolved by the I18nGrailsPlugin
Ant.copy(todir: "${basedir}/grails-app/i18n", overwrite: true) {
    fileset(dir: "${pluginBasedir}/grails-app/i18n")
}