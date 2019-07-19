package com.app.social.login.service;

import org.springframework.social.google.api.userinfo.GoogleUserInfo;

public interface GoogleService {
	 String googleLogin();
	   String  getGoogleAccessToken(String code);
	   GoogleUserInfo getGoogleUserProfile(String accessToken);

}
