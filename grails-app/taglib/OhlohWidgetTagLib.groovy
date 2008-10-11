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
class OhlohWidgetTagLib {
	static namespace = "ohlohWidget"
	
	def thinBadge = { attrs, body ->
		if(!attrs.idOhloh) throw new IllegalStateException("Ohloh identifier must be set!")
		def idOhloh = attrs.idOhloh
		out << """
			<script type="text/javascript" src="https://www.ohloh.net/projects/$idOhloh/widgets/project_thin_badge"></script>
		"""
	}
	
	def partnerBadge = { attrs, body ->
		if(!attrs.idOhloh) throw new IllegalStateException("Ohloh identifier must be set!")
		def idOhloh = attrs.idOhloh
		out << """
			<script type="text/javascript" src="https://www.ohloh.net/projects/$idOhloh/widgets/project_partner_badge"></script>
		"""
	}
	
	def languages = { attrs, body ->
		if(!attrs.idOhloh) throw new IllegalStateException("Ohloh identifier must be set!")
		def idOhloh = attrs.idOhloh
		out << """
			<script type="text/javascript" src="https://www.ohloh.net/projects/$idOhloh/widgets/project_languages"></script>
		"""
	}
	
	def factoids = { attrs, body ->
		if(!attrs.idOhloh) throw new IllegalStateException("Ohloh identifier must be set!")
		def idOhloh = attrs.idOhloh
		out << """
			<script type="text/javascript" src="https://www.ohloh.net/projects/$idOhloh/widgets/project_factoids"></script>
		"""
	}
	
	
	def cocomo = { attrs, body ->
		if(!attrs.idOhloh) throw new IllegalStateException("Ohloh identifier must be set!")
		def idOhloh = attrs.idOhloh
		out << """
			<script type="text/javascript" src="https://www.ohloh.net/projects/$idOhloh/widgets/project_cocomo"></script>
		"""
	}
	
	
	def stats = { attrs, body ->
		if(!attrs.idOhloh) throw new IllegalStateException("Ohloh identifier must be set!")
		def idOhloh = attrs.idOhloh
		out << """
			<script type="text/javascript" src="https://www.ohloh.net/projects/$idOhloh/widgets/project_basic_stats"></script>
		"""
	}
	
	
	
	
}
