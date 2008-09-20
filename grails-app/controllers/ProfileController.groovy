class ProfileController {
	def authenticateService
	def index = {
		redirect(action: my, params: params)
	}
	def my = {
		[me:authenticateService.userDomain()]
	}
}
