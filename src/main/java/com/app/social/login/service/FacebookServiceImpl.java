package com.app.social.login.service;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements FacebookService {
    
	@Value("${spring.social.facebook.appId}")
	private String facebookId;
	@Value("${spring.social.facebook.appSecret}")
	private String facebookSecret;
	
	private FacebookConnectionFactory createFacebookConnection()
	{
		return new FacebookConnectionFactory(facebookId,facebookSecret);
	}
	
	 @Override
	public String facebookLogin() {
		// TODO Auto-generated method stub
		System.out.println("hiii"+facebookId+"="+facebookSecret);
		OAuth2Parameters parameter=new OAuth2Parameters();
		parameter.setRedirectUri("http://localhost:4242/facebook");
		parameter.setScope("public_profile,email");
		return createFacebookConnection().getOAuthOperations().buildAuthenticateUrl(parameter);
	}
	   @Override
	   public String  getFacebookAccessToken(String code)
	        {
		   System.out.println("/getFacebookAccessToken");
	        return 	createFacebookConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:4242/facebook", null).getAccessToken();
	        }
	   @Override 
	   public User getFacebookUserProfile(String accessToken) 
	   {
        Facebook facebook=new FacebookTemplate(accessToken);
        String field[]= { "id", "about", "age_range", 
                "birthday", "context", "cover", 
                "currency", "devices", "education",
                "email", "favorite_athletes",
                "favorite_teams", "first_name", "gender",
                "hometown", "inspirational_people",
                "installed", "install_type", "is_verified", 
                "languages", "last_name", "link", "locale", 
                "location", "meeting_for", "middle_name", 
                "name", "name_format", "political", 
                "quotes", "payment_pricepoints", "relationship_status",
                "religion", "security_settings", "significant_other",
                "sports", "test_group", "timezone", "third_party_id",
                "updated_time", "verified", "video_upload_limits",
                "viewer_can_send_gift", "website", "work"};
    /*    byte[] profileImage = facebook.userOperations().getUserProfileImage();
        ByteArrayInputStream bis = new ByteArrayInputStream(profileImage);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("../Documents/ineotrust/output.jpg") );
        System.out.println("image created");*/
        return facebook.fetchObject("me",User.class,field);
	   }
	   
	 
}
