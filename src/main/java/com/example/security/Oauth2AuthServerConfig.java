package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${user.oauth.user.username}")
    String clientId;

    @Value("${user.oauth.user.password}")
    String clientSecret;

    @Value("${user.oauth.tokenValidityInSeconds}")
    int tokenValiditySeconds;

    @Value("${user.oauth.refreshTokenValidityInSeconds}")
    int refreshTokenValiditySeconds;

    final AuthenticationManager authenticationManager;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public Oauth2AuthServerConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId).secret(passwordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(tokenValiditySeconds)
                .authorities("CLIENT")
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
                .scopes("read");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
