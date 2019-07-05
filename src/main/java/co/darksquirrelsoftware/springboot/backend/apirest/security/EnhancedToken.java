package co.darksquirrelsoftware.springboot.backend.apirest.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import co.darksquirrelsoftware.springboot.backend.apirest.model.entity.User;
import co.darksquirrelsoftware.springboot.backend.apirest.model.service.UserService;

@Component
public class EnhancedToken implements TokenEnhancer {

	private UserService userService;

	@Autowired
	public EnhancedToken(UserService userService) {
		this.userService = userService;
	}

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = userService.findByUsername(authentication.getName());

		Map<String, Object> info = new HashMap<String, Object>();
		info.put("name", user.getName());
		info.put("email", user.getEmail());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
