package com.app.social.login.service;

import org.springframework.social.linkedin.api.LinkedInProfile;

public interface LinkedInService {
	 String linkedInLogin();
	 String  getLinkedInAccessToken(String code);
	 LinkedInProfile getLinkedInUserProfile(String accessToken);

}
