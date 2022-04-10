package com.rubypaper.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Autowired
	public void authenticate(AuthenticationManagerBuilder authBuilder) throws Exception {
		// 메모리에 테스트용 임시 계정을 추가한다.
//		authBuilder.inMemoryAuthentication().withUser("manager").password("{noop}manager123").roles("MANAGER")
//		.and()
//		.withUser("admin").password("{noop}admin123").roles("ADMIN");

		// 데이터베이스 사용자 계정을 통한 인증
		String query1 = "select username, concat('{noop}', password) password, "
						+ "true enabled from member where username = ?";
		String query2 = "select username, role from member where username = ?";

		authBuilder.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1)
				.authoritiesByUsernameQuery(query2).rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf 토근전달 비활성화
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/member/**").authenticated();
		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		// 시큐리티가 제공하는 기본 로그인 화면을 띄운다
//		http.formLogin();
		// 사용자 정의 로그인 화면을 띄운다.
		http.formLogin().loginPage("/login").defaultSuccessUrl("/success");

		// 권한 없는 사용자 페이지 연결.
		http.exceptionHandling().accessDeniedPage("/denied");

		// 로그아웃 설정
		http.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		
		// 사용자 정의 UserDetailsService 객체를 사용하도록 등록한다.
		http.userDetailsService(userDetailsService);
	}
}
