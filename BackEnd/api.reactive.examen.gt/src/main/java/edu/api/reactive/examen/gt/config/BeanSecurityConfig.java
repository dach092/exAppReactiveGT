package edu.api.reactive.examen.gt.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import edu.api.reactive.examen.gt.jwt.JWTTokenProvider;
import edu.api.reactive.examen.gt.token.TokenAuthenticationConverter;
import edu.api.reactive.examen.gt.jwt.*;
import static edu.api.reactive.examen.gt.constants.JWTConstants.*;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class BeanSecurityConfig {

	@Autowired
	private ReactiveUserDetailsService reactiveUserDetailsService;

	@Autowired
	private JWTTokenProvider jWTtokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.applyPermitDefaultValues();

		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
		ccs.registerCorsConfiguration("/**", corsConfiguration);

		return ccs;
	}

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

		http.httpBasic().disable().formLogin().disable().csrf().disable().logout().disable();

		http.cors().configurationSource(urlBasedCorsConfigurationSource());

		http.addFilterAt(webFilter(), SecurityWebFiltersOrder.AUTHORIZATION).authorizeExchange()
				.pathMatchers(HttpMethod.POST, LOGIN_URL).permitAll().pathMatchers(AUTH_WHITELIST).permitAll()
				.anyExchange().authenticated();

		return http.build();
	}

	@Bean
	public AuthenticationWebFilter webFilter() {
		AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(
				repositoryReactiveAuthenticationManager());

		authenticationWebFilter.setServerAuthenticationConverter(new TokenAuthenticationConverter(jWTtokenProvider));

		authenticationWebFilter.setRequiresAuthenticationMatcher(new JWTHeadersExchangeMatcher());

		authenticationWebFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());

		return authenticationWebFilter;
	}

	@Bean
	public JWTReactiveAuthenticationManager repositoryReactiveAuthenticationManager() {
		JWTReactiveAuthenticationManager repositoryReactiveAuthenticationManager = new JWTReactiveAuthenticationManager();
		return repositoryReactiveAuthenticationManager;
	}
}
