package com.app.social.login.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class LinkedInServiceImpl implements LinkedInService {
	@Value("${spring.social.linkedin.appId}")
	private String linkedinId;
	@Value("${spring.social.linkedin.appSecret}")
	private String linkedinSecret;
	
	private LinkedInConnectionFactory createLikedInConnection()
	{
		return new LinkedInConnectionFactory(linkedinId,linkedinSecret);
	}
	
	
	

	@Override
	public String linkedInLogin() {
	
		OAuth2Parameters parameter=new OAuth2Parameters();
		parameter.setRedirectUri("http://localhost:4242/linkedin");
		parameter.setScope("r_basicprofile");
		return createLikedInConnection().getOAuthOperations().buildAuthenticateUrl(parameter);
	}

	@Override
	public String getLinkedInAccessToken(String code) {
		System.out.println("getLinkedInAccessToken"); 
		{
		 return 	createLikedInConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:4242/linkedin", null).getAccessToken();
		 }
	}

	@Override
	public LinkedInProfile getLinkedInUserProfile(String accessToken) {
		System.out.println("access token"+accessToken);
		LinkedIn likedIn=new LinkedInTemplate(accessToken);
	
     String s =likedIn.profileOperations().getProfileUrl();
		System.out.println("name=="+s);
		LinkedInProfile p=likedIn.profileOperations().getUserProfile();
		return p;
	}

}
