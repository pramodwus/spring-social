package com.app.social.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.app.social.login.model.UserInfo;
import com.app.social.login.service.GoogleServiceImpl;
@Controller
public class GoogleController {

	@Autowired
	 private GoogleServiceImpl googleservice;
	 
	 @GetMapping("/googlelogin")
	 public RedirectView getGoogle()
	 {
		 System.out.println("hiii");
		 RedirectView view=new RedirectView();
		 String url=googleservice.googleLogin();
		 System.out.println("hii"+url);
		 view.setUrl(url);
		 return view;
	 }
	 @GetMapping("/google")
	 public String facebook(@RequestParam("code") String code)
	 {
		 System.out.println("/inside google");
		String acessToken=googleservice.getGoogleAccessToken(code);
		 return "redirect:/googleprofiledata/"+acessToken;
	 }
	 
	 @GetMapping("/googleprofiledata/{accessToken:.+}")
	 public String facebookProfileData(@PathVariable String accessToken,Model model) 
	 {
		 System.out.println("/googleProfileData google");
		
		 GoogleUserInfo user=googleservice.getGoogleUserProfile(accessToken);
		 System.err.println(user.getFirstName()+user.getEmail()+user.getLastName()+user.getFirstName()+user.getGender()+user.getName()+user.getEmail()+user.getId());
		 UserInfo userInfo=new UserInfo();
		 userInfo.setFirstName(user.getFirstName());
		 userInfo.setLastName(user.getLastName());
		 userInfo.setEmail(user.getEmail());
		 userInfo.setImageUrl(user.getProfilePictureUrl());
		 model.addAttribute("user",userInfo);
		 return "userprofile";
	 }
	 
}
