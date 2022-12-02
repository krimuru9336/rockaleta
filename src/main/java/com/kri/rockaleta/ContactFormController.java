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

	@GetMapping("/")
	public String getContactForm(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "index";
	}

	@PostMapping("/saveContactForm")
	public String contactFormSubmit(@ModelAttribute ContactForm contactForm, Model model) {
		model.addAttribute("contactForm", contactForm);

		return "redirect:/#contact";
	}

	
}
