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
class MapTagLib {
	static namespace = "maps"
	
	def geoLocalizationService
	
	def map = {attrs ->
		def geolocalization = attrs.geolocalization
		log.debug "geolocalization: " + geolocalization
		if(geolocalization) {
			def geodata = geoLocalizationService.map(geolocalization)
			log.debug "geodata: " + geodata
			if(geodata) {
				def keyGM = grailsApplication.config.googlemaps.key
				out << resource.googlemaps(key:keyGM)
				out << richui.googlemaps(lat:geodata["latitude"], lng:geodata["longitude"])
			}
		}
	}
}
