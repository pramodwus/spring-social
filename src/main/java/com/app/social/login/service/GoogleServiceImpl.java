package com.app.social.login.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class GoogleServiceImpl implements GoogleService {
	
	@Value("${spring.social.google.appId}")
	private String googleId;
	@Value("${spring.social.google.appSecret}")
	private String gooogleSecret;
	
	private GoogleConnectionFactory createGoogleConnection()
	{
		return new GoogleConnectionFactory(googleId,gooogleSecret);
	}
	
	@Override
	public String googleLogin() {
		System.out.println("hiii"+googleId+"="+gooogleSecret);
		OAuth2Parameters parameter=new OAuth2Parameters();
		parameter.setRedirectUri("http://localhost:4242/google");
		parameter.setScope("profile");
		parameter.setScope("email");
		return createGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameter);
	}
	
	@Override
	public String getGoogleAccessToken(String code) {
		 {
		        return 	createGoogleConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:4242/google", null).getAccessToken();
		        }
	}
   
	@Override
	public GoogleUserInfo getGoogleUserProfile(String accessToken) {
		System.out.println("access token"+accessToken);
		Google google=new GoogleTemplate(accessToken);
		System.out.println("access "+google);
		GoogleUserInfo p=google.userOperations().getUserInfo();
		System.out.println("name=="+p.getFirstName());
		return p;
	}
}
