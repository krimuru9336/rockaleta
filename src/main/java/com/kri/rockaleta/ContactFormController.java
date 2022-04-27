package com.kri.rockaleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactFormController {

	@Autowired
	EmailSenderService emailSenderService;

	@GetMapping("/")
	public String getContactForm(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "index";
	}

	@PostMapping("/saveContactForm")
	public String contactFormSubmit(@ModelAttribute ContactForm contactForm, Model model) {
		model.addAttribute("contactForm", contactForm);
		System.out.println("Data:" + contactForm.getPhoneNumber());

		sendEmailFromContactFormData(contactForm.getRequestorName(), contactForm.getEmail(),
				contactForm.getPhoneNumber(), contactForm.getNotes());

		

		System.out.println("In Controller Post");

		return "redirect:/#contact";
	}

	private void sendEmailFromContactFormData(String requestorName, String email, String phoneNumber, String notes) {
		String emailBody = "Name: " + requestorName + "\n Email: " + email + "\n Phone Number: " + phoneNumber
				+ "\n Notes: " + notes;
		
		emailSenderService.sendEmail(email, emailBody, "TEST!!!");

	}
}
