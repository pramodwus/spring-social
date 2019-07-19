package com.app.social.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	
	@GetMapping(value="/")
    public ModelAndView getPage()
    {
	ModelAndView model=	new ModelAndView();
     model.setViewName("home");
     return model;
    }
}
