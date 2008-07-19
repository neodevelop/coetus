class BootStrap {
	def init = { servletContext ->
		
		
		
		
//		new Author(name:"Stephen King").addToBooks(new Book(title:"The Stand")).addToBooks(new Book(title:"The Shining")).save()
				
				
		def evento = new Event()
		def domingo = new Speaker()
		def andres = new Speaker()
		
		def grailsT = new Talk()
		def groovyT = new Talk()
		def compartida = new Talk()
		
		if(Event.count() == 0) {
			println "Agregando evento prueba"
			
			evento = new Event(name:"Evento Prueba", description:"Evento dummy", location:"Por alla")
			evento.save()
		}
		
		if(Speaker.count() == 0) {
			
			compartida = new Talk(event:evento, title:"Presentacion del GUG", summary:"Chido", location:"Por alla")
			compartida.save()
			
			groovyT = new Talk(event:evento, title:"Introduccion a Groovy", summary:"Chido", location:"Por alla")
			groovyT.save()
			
			grailsT = new Talk(event:evento, title:"Introduccion a Grails", summary:"Chido", location:"Por alla")
			grailsT.save()
			
			domingo = new Speaker(name:"Domingo Suarez", bio:"Chido", blog:"http://www.domix.org", email:"domingo.suarez@gmail.com", company:"Bursatec")
			domingo.save()
			
			andres = new Speaker(name:"Andres Almiray", bio:"Chido", blog:"http://www.jroller.com/aalmiray/", email:"aalmiray@yahoo.com", company:"Oracle")
			andres.save()
			
			//compartida.addToSpeakers(andres)
			//compartida.addToSpeakers(domingo)
			andres.addToTalks(compartida).save()
			domingo.addToTalks(compartida).save()
			
			andres.addToTalks(groovyT).save()
			
			domingo.addToTalks(grailsT).save()
			
			
			println "Charlas de andres: " + andres.talks.size()
			println "Oradores de la charla compartida: " + compartida.speakers.size()
			
			//andres.addToTalks(groovyT).save()
			
			//domingo.addToTalks(grailsT).save()
			
			
		}
     }
     def destroy = {
     }
} 