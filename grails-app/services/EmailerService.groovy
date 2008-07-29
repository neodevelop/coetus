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
import javax.mail.MessagingException

import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage

/**
 * Simple service for sending emails.
 *
 * Work is planned in the Grails roadmap to implement first-class email
 * support, so there's no point in making this code any more sophisticated.
 *
 * @author Haotian Sun
 */
class EmailerService {

	boolean transactional = false

	MailSender mailSender
	SimpleMailMessage mailMessage // a "prototype" email instance

	/**
   	 * Send a list of emails
   	 *
   	 * @param mails a list of maps
   	 */
   	def sendEmails(mails) {

		// Build the mail messages
		def messages = []
		for (mail in mails) {
			// Create a thread safe "sandbox" of the message
			SimpleMailMessage message = new SimpleMailMessage(mailMessage)
			message.to = mail.to
			message.text = mail.text
			message.subject = mail.subject
			messages << message
		}
		// Send them all together
		try {
			log.debug "about to send ${messages.size()} messages to:\n${messages.to.join('\n')}"
			mailSender.send(messages as SimpleMailMessage[])
		}
		catch (MailException e) {
			log.error "Failed to send emails", e
		}
		catch (MessagingException e) {
			log.error "Failed to send emails", e
		}
	}
}
