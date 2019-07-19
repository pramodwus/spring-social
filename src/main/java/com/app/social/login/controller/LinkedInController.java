package com.app.social.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.app.social.login.model.UserInfo;
import com.app.social.login.service.LinkedInServiceImpl;

@Controller
public class LinkedInController {
	@Autowired
	 private LinkedInServiceImpl likedinservice;
	 
	 @GetMapping("/linkedinlogin")
	 public RedirectView getFacebook()
	 {
		 System.out.println("hiii");
		 RedirectView view=new RedirectView();
		 String url=likedinservice.linkedInLogin();
		 System.out.println("hii"+url);
		 view.setUrl(url);
		 return view;
	 }
	 @GetMapping("/linkedin")
	 public String facebook(@RequestParam("code") String code)
	 {
		 System.out.println("/inside likedin");
		String acessToken=likedinservice.getLinkedInAccessToken(code);
		 return "redirect:/linkedinprofiledata/"+acessToken;
	 }
	 
	 @GetMapping("/linkedinprofiledata/{accessToken:.+}")
	 public String facebookProfileData(@PathVariable String accessToken,Model model) 
	 {
		 System.out.println("/linkedinProfileData likedin");
		
		 LinkedInProfile user=likedinservice.getLinkedInUserProfile(accessToken);
	//	System.err.println(user.getFirstName()+user.getMiddleName()+user.getLastName()+user.getAbout()+user.getAbout()+user.getBio()+user.getBirthday()+user.getEmail()+user.getEducation()+user.getGender()+user.getCover()+user.getReligion());
		 UserInfo userInfo=new UserInfo();
		 userInfo.setFirstName(user.getFirstName());
		 userInfo.setLastName(userInfo.getLastName());
		 userInfo.setEmail(user.getEmailAddress());
		 
		 userInfo.setImageUrl(user.getProfilePictureUrl());
		 model.addAttribute("user",userInfo);
		 return "userprofile";
	 }
	 

}
