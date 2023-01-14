package com.thesis.scheduling.businesslevel.config;

import java.util.Collections;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.thesis.scheduling.businesslevel.config.token.TokenFilterConfiguerer;
import com.thesis.scheduling.businesslevel.config.token.TokenService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final TokenService tokenService;

	private final String[] ANONYMOUS = { "/member/anonymous/**" };
	 
	private final String[] PUBLIC = { "/organization/public/**" , "/title/public/**" };

	public SecurityConfig(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors(config -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowCredentials(true);
            cors.setAllowedOriginPatterns(Collections.singletonList("http://*"));
            cors.addAllowedHeader("*");
            cors.addAllowedMethod("GET");
            cors.addAllowedMethod("POST");
            cors.addAllowedMethod("PUT");
            cors.addAllowedMethod("DELETE");
            cors.addAllowedMethod("OPTIONS");

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", cors);

            config.configurationSource(source);
        	}).csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests().antMatchers(ANONYMOUS).anonymous()
			.and().authorizeRequests().antMatchers(PUBLIC).permitAll()
			.anyRequest().authenticated()
			.and().apply(new TokenFilterConfiguerer(tokenService));
	}

}
