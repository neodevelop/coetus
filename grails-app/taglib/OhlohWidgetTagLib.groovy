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
