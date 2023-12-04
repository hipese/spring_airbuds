package com.kdt.config;

import javax.sql.DataSource;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kdt.services.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberService mService;
	
	
	@Bean
	protected SecurityFilterChain config(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeHttpRequests()
//		설정하는 부분
//		.requestMatchers(new AntPathRequestMatcher("/manager/only")).hasAnyRole("MANAGER")
//		.requestMatchers(new AntPathRequestMatcher("/admin/manage")).hasAnyRole("MANAGER","ADMIN")
//		.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
//		.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER","ADMIN")
//		.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();
		
		http.logout().invalidateHttpSession(true).logoutSuccessUrl("/");
		http.exceptionHandling().accessDeniedPage("/denied");
		http.formLogin().loginPage("/login");
		http.userDetailsService(mService);
		
		
		return http.build();
	}
}
