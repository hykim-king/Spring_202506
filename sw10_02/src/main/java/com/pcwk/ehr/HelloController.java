package com.pcwk.ehr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	
	
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	public String hello(Model model) {
		System.out.println("HelloController, hello!");
		
		model.addAttribute("message", "Hello, Spring MVC!");
		
		
		return "hello";
		
	}
}
