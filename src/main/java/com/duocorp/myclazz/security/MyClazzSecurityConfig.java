package com.duocorp.myclazz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * The Class MyClazzSecurityConfig.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class MyClazzSecurityConfig extends WebSecurityConfigurerAdapter {

	/** The login service. */
	@Autowired
	MyClazzLoginService loginService;

	/** The success handler. */
	@Autowired
	LoginSuccessHandler successHandler;

	/** The failure handler. */
	@Autowired
	LoginFailureHandler failureHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config
	 * .annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().cors().and().csrf().disable().authorizeRequests().antMatchers("/login", "/").permitAll()
				.anyRequest().authenticated().and().formLogin().successHandler(successHandler)
				.failureHandler(failureHandler).and().logout().logoutUrl("/myclazzApi/logout")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID");

	}

	// /**
	// * Csrf token repository.
	// *
	// * @return the csrf token repository
	// */
	// private CsrfTokenRepository csrfTokenRepository() {
	// HttpSessionCsrfTokenRepository repository = new
	// HttpSessionCsrfTokenRepository();
	// repository.setHeaderName("X-XSRF-TOKEN");
	// return repository;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config
	 * .annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// web.ignoring().antMatchers("/login");
	}

	/**
	 * Cors configuration source.
	 *
	 * @return the cors configuration source
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}