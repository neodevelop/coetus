class EventController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ eventList: Event.list( params ) ]
    }

    def show = {
        def event = Event.get( params.id )

        if(!event) {
            flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ event : event ] }
    }

    def delete = {
        def event = Event.get( params.id )
        if(event) {
            event.delete()
            flash.message = "event.deleted"
            flash.args = [params.id]
            flash.defaultMessage = "Event ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def event = Event.get( params.id )

        if(!event) {
            flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ event : event ]
        }
    }

    def update = {
        def event = Event.get( params.id )
        if(event) {
            event.properties = params
            if(!event.hasErrors() && event.save()) {
                flash.message = "event.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Event ${params.id} updated"
                redirect(action:show,id:event.id)
            }
            else {
                render(view:'edit',model:[event:event])
            }
        }
        else {
            flash.message = "event.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Event not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def event = new Event()
        event.properties = params
        return ['event':event]
    }

    def save = {
        def event = new Event(params)
        if(!event.hasErrors() && event.save()) {
            flash.message = "event.created"
            flash.args = ["${event.id}"]
            flash.defaultMessage = "Event ${event.id} created"
            redirect(action:show,id:event.id)
        }
        else {
            render(view:'create',model:[event:event])
        }
    }
}