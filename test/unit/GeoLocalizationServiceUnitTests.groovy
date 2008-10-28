import groovy.mock.interceptor.*

class GeoLocalizationServiceUnitTests extends GroovyTestCase {
	GeoLocalizationService service
	
	/** Setup metaclass fixtures for mocking. */
	void setUp() {
		def logger = new Expando( debug: { println it }, info: { println it }, warn: { println it }, error: { println it } )
		GeoLocalizationService.metaClass.getLog = { -> logger }
		service = new GeoLocalizationService()
	}
	
	/** Remove metaclass fixtures for mocking. */
	void tearDown() {
		def remove = GroovySystem.metaClassRegistry.&removeMetaClass
		remove GeoLocalizationService
		remove String
	}
	
	void testMapSuccess() {
		def location = "2344,5665"
		def map = service.map(location)
		assertNotNull(map)
		assertEquals(2,map.size())
	}
	
	void testMapEmptyFail() {
		def location = ""
		def map = service.map(location)
		assertNull(map)
	}
	
	void testMapNullFail() {
		def location = null
		def map = service.map(location)
		assertNull(map)
	}
}
