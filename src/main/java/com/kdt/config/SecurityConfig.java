package com.kdt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kdt.services.MemberService;

import jakarta.servlet.http.HttpServletResponse;

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
		
		// JSP 버전
//		http.logout().invalidateHttpSession(true).logoutSuccessUrl("/");
//		http.exceptionHandling().accessDeniedPage("/denied");
//		http.formLogin().loginPage("/login");
		
		// RestFul
		// login URL은 아래와 같다.
		// Controller에 해당 URL에 대한 처리를 선언해줄 필요 없음
		http.formLogin().loginProcessingUrl("/api/member/login")

				// username 파라미터 이름은 아래와 같다고 선언
				.usernameParameter("id")

				// password 파라미터 이름은 아래와 같다고 선언
				.passwordParameter("password")

				// 성공했을 때 핸들러
				.successHandler((request, response, authentication) -> {
					System.out.println("로그인 성공");
					// response에 성공 했다고 정보를 담아 줌
					response.setStatus(HttpServletResponse.SC_OK);
				})

				// 실패했을 때 핸들러
				.failureHandler((request, response, exception) -> {
					System.out.println("로그인 실패");
					// reponse에 실패 했다고 정보를 담아 줌
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				});

		// 로그인 안된 사용자가 요청 했을때 발생하는 핸들러
		http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		});

		// 로그아웃 경로 매핑 및 핸들러 작성
		http.logout().logoutUrl("/api/member/logout").invalidateHttpSession(true)
				.logoutSuccessHandler((request, response, authentication) -> {
					System.out.println("로그아웃 성공");
					response.setStatus(HttpServletResponse.SC_OK);
				});
		
		
		http.userDetailsService(mService);
		
		
		return http.build();
	}
	
	// Bean 으로 선언만 해줘도 security에서 알아서 비밀번호를 인코딩 해 줌
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
