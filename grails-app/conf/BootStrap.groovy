class BootStrap {
	def init = { servletContext ->
		
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
		
		if(Person.count() == 0) {
			
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
			
			
			def usr1 = new Person(username:"jkings",userRealName:"Juanelo", passwd:"pwd", email:"reyesmjm@gmail.com", company:"IFE", bio:"Chido", blog:"http://www.reyesmjm.org")
			usr1.save()
			def usr2 = new Person(username:"jordi",userRealName:"Jorge", passwd:"pwd", email:"jorge@gmail.com", company:"IFE", bio:"Chido", blog:"http://www.reyesmjm.org")
			usr2.save()
			
			
			def asistente1 = new Attendee(person:usr1, event:evento)
			asistente1.save()
			
			def asistente2 = new Attendee(person:usr2, event:evento)
			asistente2.save()
			
			asistente1.addToTalks(compartida)
			asistente1.addToTalks(groovyT)
			asistente1.save()
			
			asistente2.addToTalks(compartida)
			asistente1.addToTalks(grailsT)
			asistente2.save()
			
			
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