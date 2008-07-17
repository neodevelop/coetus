class TalkController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ talkList: Talk.list( params ) ]
    }

    def show = {
        def talk = Talk.get( params.id )

        if(!talk) {
            flash.message = "talk.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Talk not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ talk : talk ] }
    }

    def delete = {
        def talk = Talk.get( params.id )
        if(talk) {
            talk.delete()
            flash.message = "talk.deleted"
            flash.args = [params.id]
            flash.defaultMessage = "Talk ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "talk.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Talk not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def talk = Talk.get( params.id )

        if(!talk) {
            flash.message = "talk.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Talk not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ talk : talk ]
        }
    }

    def update = {
        def talk = Talk.get( params.id )
        if(talk) {
            talk.properties = params
            if(!talk.hasErrors() && talk.save()) {
                flash.message = "talk.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Talk ${params.id} updated"
                redirect(action:show,id:talk.id)
            }
            else {
                render(view:'edit',model:[talk:talk])
            }
        }
        else {
            flash.message = "talk.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Talk not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def talk = new Talk()
        talk.properties = params
        return ['talk':talk]
    }

    def save = {
        def talk = new Talk(params)
        if(!talk.hasErrors() && talk.save()) {
            flash.message = "talk.created"
            flash.args = ["${talk.id}"]
            flash.defaultMessage = "Talk ${talk.id} created"
            redirect(action:show,id:talk.id)
        }
        else {
            render(view:'create',model:[talk:talk])
        }
    }
}