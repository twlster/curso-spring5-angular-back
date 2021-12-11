package com.bolsadeideas.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/clients", "/api/clients/page/**","/api/clients/upload/img/**","/images/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/bills/**", "/api/clients/{id}").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/clients/{id}").hasAnyRole("USER","ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/clients/upload").hasAnyRole("USER","ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/clients").hasAnyRole("ADMIN")
//		.antMatchers("/api/clients/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		
		UrlBasedCorsConfigurationSource basedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		basedCorsConfigurationSource.registerCorsConfiguration("/**", config);
		
		return basedCorsConfigurationSource;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filter;
	}
	
	
}
