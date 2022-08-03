package com.kri.rockaleta;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		sendEmailFromContactFormData(contactForm.getName(), contactForm.getEmail(), contactForm.getPhone(),
				contactForm.getNotes());

		System.out.println("In Controller Post");
//		session.setAttribute("mySessionAttribute", "Email gesendet");

		return "redirect:/#contact";
	}

	private void sendEmailFromContactFormData(String name, String email, String phone, String notes) {
		String emailBody = "Name: " + name + "\n Email: " + email + "\n Phone Number: " + phone + "\n Notes: " + notes;
		System.out.println("Email Body: " + emailBody);
		emailSenderService.sendEmail(email, emailBody, "Test");

	}
}
