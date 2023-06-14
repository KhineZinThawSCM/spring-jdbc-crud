package com.ojt.springjdbc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author khinezinthaw
 *
 */
@Controller
public class HomeController {

	/**
	 * Welcome
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("message", "Hello Spring gfdgdfgfdMVC");
		return mv;
	}
}
