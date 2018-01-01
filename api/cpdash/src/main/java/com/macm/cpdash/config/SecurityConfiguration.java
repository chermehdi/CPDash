package com.macm.cpdash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.macm.cpdash.security.JwtAuthFilter;
import com.macm.cpdash.security.JwtUtils;
import com.macm.cpdash.security.UnauthorizedRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UnauthorizedRequest unauthorizedRequest;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Setting BCryptPasswordEncoder as our default PasswordEncoder .
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Setting the AuthFilter to be used before every auth request (see security
	 * config above)
	 */

	@Bean
	public JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter();
	}

	@Bean
	public JwtUtils jwtUtils() {
		return new JwtUtils();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedRequest).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/**").permitAll().antMatchers("/api/**").authenticated().anyRequest()
				.permitAll();

		http.addFilterBefore(jwtAuthFilter(),
		UsernamePasswordAuthenticationFilter.class);

		http.headers().cacheControl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
