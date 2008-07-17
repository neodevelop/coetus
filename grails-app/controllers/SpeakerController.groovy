class SpeakerController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ speakerList: Speaker.list( params ) ]
    }

    def show = {
        def speaker = Speaker.get( params.id )

        if(!speaker) {
            flash.message = "speaker.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Speaker not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ speaker : speaker ] }
    }

    def delete = {
        def speaker = Speaker.get( params.id )
        if(speaker) {
            speaker.delete()
            flash.message = "speaker.deleted"
            flash.args = [params.id]
            flash.defaultMessage = "Speaker ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "speaker.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Speaker not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def speaker = Speaker.get( params.id )

        if(!speaker) {
            flash.message = "speaker.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Speaker not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ speaker : speaker ]
        }
    }

    def update = {
        def speaker = Speaker.get( params.id )
        if(speaker) {
            speaker.properties = params
            if(!speaker.hasErrors() && speaker.save()) {
                flash.message = "speaker.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Speaker ${params.id} updated"
                redirect(action:show,id:speaker.id)
            }
            else {
                render(view:'edit',model:[speaker:speaker])
            }
        }
        else {
            flash.message = "speaker.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Speaker not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def speaker = new Speaker()
        speaker.properties = params
        return ['speaker':speaker]
    }

    def save = {
        def speaker = new Speaker(params)
        if(!speaker.hasErrors() && speaker.save()) {
            flash.message = "speaker.created"
            flash.args = ["${speaker.id}"]
            flash.defaultMessage = "Speaker ${speaker.id} created"
            redirect(action:show,id:speaker.id)
        }
        else {
            render(view:'create',model:[speaker:speaker])
        }
    }
}