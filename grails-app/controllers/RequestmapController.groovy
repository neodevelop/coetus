/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.springframework.util.StringUtils

/**
 * Requestmap controller.
 */
class RequestmapController {

	// the delete, save and update actions only accept POST requests
	def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect(action: list, params: params)
	}

	def list = {
		if (!params.max) {
			params.max = 10
		}
		[requestmapList: Requestmap.list(params)]
	}

	def show = {
		[requestmap: Requestmap.get(params.id)]
	}

	def delete = {
		def requestmap = Requestmap.get(params.id)
		if (!requestmap) {
			flash.message = "Requestmap not found with id ${params.id}"
			redirect(action:list)
			return
		}

		requestmap.delete()
		flash.message = "Requestmap ${params.id} deleted."
		redirect(action: list)
	}

	def edit = {
		def requestmap = Requestmap.get(params.id)
		if (!requestmap) {
			flash.message = "Requestmap not found with id ${params.id}"
			redirect(action: list)
			return
		}

		[requestmap: requestmap]
	}

	/**
	 * Update action, called when an existing Requestmap is updated.
	 */
	def update = {

		def requestmap = Requestmap.get(params.id)
		if (!requestmap) {
			flash.message = "Requestmap not found with id ${params.id}"
			redirect(action: edit, id :params.id)
			return
		}

		updateFromParams(requestmap)
		if (requestmap.save()) {
			redirect(action: show, id: requestmap.id)
		}
		else {
			render(view: 'edit', model: [requestmap: requestmap])
		}
	}

	def create = {
		def requestmap = new Requestmap()
		requestmap.properties = params
		[requestmap: requestmap]
	}

	/**
	 * Save action, called when a new Requestmap is created.
	 */
	def save = {

		def requestmap = new Requestmap()
		updateFromParams(requestmap)
		if (requestmap.save()) {
			redirect(action: show, id: requestmap.id)
		}
		else {
			render(view: 'create', model: [requestmap: requestmap])
		}
	}

	private void updateFromParams(requestmap) {
		requestmap.properties = params
		//get user's enter field "configAttribute" from the params.
		String[] configAttrs = StringUtils.commaDelimitedListToStringArray(params.configAttribute)
		//Format the configAttributes to meet Spring Security's requirement.
		String formattedConfigAttrs = ''
		String delimiter = ''
		for (String configAttribute in configAttrs) {
			if (configAttribute.trim().length() > 0) {
				formattedConfigAttrs += delimiter + 'ROLE_' + configAttribute.toUpperCase()
				delimiter = ','
			}
		}
		requestmap.configAttribute = formattedConfigAttrs
	}
}
