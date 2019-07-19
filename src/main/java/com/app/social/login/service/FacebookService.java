package com.app.social.login.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {

   String facebookLogin();
   String  getFacebookAccessToken(String code);
   public User getFacebookUserProfile(String accessToken);
}
