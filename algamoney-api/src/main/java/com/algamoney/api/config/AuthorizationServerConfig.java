/**
 * 
 */
package com.algamoney.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import com.algamoney.api.config.token.CustomTokenEnhancer;

/**
 * @author Morpheus
 *
 */
@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		    clients.inMemory() //inMemory() em memoria.
			.withClient("angular")
			.secret("@ngul@r0")
			 //define o escopo da autorização.
			.scopes("read", "write")
			//password flow. app recebe o usr e senha e envia para accessToken(usando o refresh para o token). 
			.authorizedGrantTypes("password" , "refresh_token") 
			// quanto tempo o tokem ficará ativo 1800s = 30 minutos.
			.accessTokenValiditySeconds(1800)
		    // quanto tempo para expirar o refresh token 3600 * 24 = 1 dia.
		    .refreshTokenValiditySeconds(3600 * 24)
		.and()
			.withClient("mobile")
			.secret("m0b1l30")
			.scopes("read")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600 * 24);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		
			endpoints
			//armazena o token para autenticação.
			.tokenStore(tokenStore())
			//conversor para jwt
			//.accessTokenConverter(accessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain)
			//não reaproveita = false .um novo refresh token seja enviado.
			.reuseRefreshTokens(false)
			.authenticationManager(authenticationManager);
	}
	
		
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		//secret base64 encoded do jwt
		accessTokenConverter.setSigningKey("algaworks");
		return accessTokenConverter;
	}
	//armazena o token para autenticação.nesse caso em memoria para o jwt.
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	//token customizado para trazer o nome do usuario Logado
	@Bean
	public TokenEnhancer tokenEnhancer() {
	    return new CustomTokenEnhancer();
	}
	
	
	//armazena o token para autenticação.nesse caso em memoria.
/*	@Bean  
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}*/
	
	
}
