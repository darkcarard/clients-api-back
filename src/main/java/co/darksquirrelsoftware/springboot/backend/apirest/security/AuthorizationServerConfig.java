package co.darksquirrelsoftware.springboot.backend.apirest.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String ANGULAR_CLIENT_NAME = "angularapp";
	private static final String ANGULAR_CLIENT_PASSWORD = "12345";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
	private static final String TOKEN_KEY_ACCESS = "permitAll()";
	private static final String CHECK_TOKEN_ACCESS = "isAuthenticated()";
	private static final String AUTHENTICATION_MANAGER_QUALIFIER = "authenticationManager";
	
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 3600;
	private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 3600;

	private BCryptPasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private EnhancedToken enhancedToken;

	@Autowired
	public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder,
			@Qualifier(AUTHENTICATION_MANAGER_QUALIFIER) AuthenticationManager authenticationManager, EnhancedToken enhancedToken) {
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.enhancedToken = enhancedToken;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess(TOKEN_KEY_ACCESS).checkTokenAccess(CHECK_TOKEN_ACCESS);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(ANGULAR_CLIENT_NAME).secret(passwordEncoder.encode(ANGULAR_CLIENT_PASSWORD))
				.scopes(SCOPE_READ, SCOPE_WRITE).authorizedGrantTypes(GRANT_TYPE_PASSWORD, GRANT_TYPE_REFRESH_TOKEN)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(enhancedToken, (TokenEnhancer)accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);
		
		return jwtAccessTokenConverter;
	}

}
