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
			
			domingo = new Person(username:"domix",userRealName:"Domingo Suarez", passwd:"pwd", email:"domingo.suarez@gmail.com", company:"Bursatec", bio:"Chido", blog:"http://www.domix.org")
			domingo.save()
			
			andres = new Person(username:"aalmiray",userRealName:"Andres Almiray", passwd:"pwd", email:"aalmiray@yahoo.com", company:"Oracle", bio:"Chido", blog:"http://www.jroller.com/aalmiray/")
			andres.save()
			
			compartida.addToSpeakers(andres)
			compartida.addToSpeakers(domingo)
			compartida.save()
			
			groovyT.addToSpeakers(andres)
			groovyT.save()
			
			grailsT.addToSpeakers(andres)
			grailsT.save()
			
			
			/*
			
			def asistente1 = new Participant(username:"domix",userRealName:"Domingo Suarez", passwd:"pwd", email:"domingo.suarez@gmail.com", company:"Bursatec",)
			
			println "Charlas de andres: " + andres.talks.size()
			println "Oradores de la charla compartida: " + compartida.speakers.size()
			*/
			
		}
     }
     def destroy = {
     }
} 