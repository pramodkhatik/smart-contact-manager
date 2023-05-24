package com.poc.SmartContactManager.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.poc.SmartContactManager.security.CustomUserDetailService;
import com.poc.SmartContactManager.security.JWTAuthenticationEntryPoint;
import com.poc.SmartContactManager.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	public static final String[] PUBLIC_URLS = { "/api/auth/**", "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**",
			"/swagger-ui/**", "/webjars/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests().antMatchers(PUBLIC_URLS).permitAll().antMatchers(HttpMethod.GET)
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();

		return defaultSecurityFilterChain;

	}
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		CorsConfigurationSource configurationSource = new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowCredentials(true);
				corsConfiguration.addAllowedOriginPattern("*");
				corsConfiguration.addAllowedHeader("Authorization");
				corsConfiguration.addAllowedHeader("Content-Type");
				corsConfiguration.addAllowedHeader("Accept");
				corsConfiguration.addAllowedMethod("POST");
				corsConfiguration.addAllowedMethod("GET");
				corsConfiguration.addAllowedMethod("DELETE");
				corsConfiguration.addAllowedMethod("PUT");
				corsConfiguration.addAllowedMethod("OPTIONS");
				corsConfiguration.setMaxAge(3600L);
				return corsConfiguration;
			}
		};

		FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(configurationSource));

		return filterRegistrationBean;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }
	
	@Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
