class ParticipantController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ participantList: Participant.list( params ) ]
    }

    def show = {
        def participant = Participant.get( params.id )

        if(!participant) {
            flash.message = "participant.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Participant not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ participant : participant ] }
    }

    def delete = {
        def participant = Participant.get( params.id )
        if(participant) {
            participant.delete()
            flash.message = "participant.deleted"
            flash.args = [params.id]
            flash.defaultMessage = "Participant ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "participant.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Participant not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def participant = Participant.get( params.id )

        if(!participant) {
            flash.message = "participant.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Participant not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ participant : participant ]
        }
    }

    def update = {
        def participant = Participant.get( params.id )
        if(participant) {
            participant.properties = params
            if(!participant.hasErrors() && participant.save()) {
                flash.message = "participant.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Participant ${params.id} updated"
                redirect(action:show,id:participant.id)
            }
            else {
                render(view:'edit',model:[participant:participant])
            }
        }
        else {
            flash.message = "participant.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Participant not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def participant = new Participant()
        participant.properties = params
        return ['participant':participant]
    }

    def save = {
        def participant = new Participant(params)
        if(!participant.hasErrors() && participant.save()) {
            flash.message = "participant.created"
            flash.args = ["${participant.id}"]
            flash.defaultMessage = "Participant ${participant.id} created"
            redirect(action:show,id:participant.id)
        }
        else {
            render(view:'create',model:[participant:participant])
        }
    }
}