package com.bolsadeideas.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.backend.apirest.models.entity.User;
import com.bolsadeideas.springboot.backend.apirest.models.services.IUserService;

@Component
public class AditionalTokenInfo implements TokenEnhancer{

	@Autowired
	private IUserService userService; 
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		
		User user = userService.findByUsername(authentication.getName());
		
		info.put("aditional info", "This is an aditional message "+authentication.getName());
		info.put("user_name",user.getName());
		info.put("user_id",user.getId());	
		info.put("user_lastname",user.getLastName());	
		info.put("user_email",user.getEmail());	
		
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
