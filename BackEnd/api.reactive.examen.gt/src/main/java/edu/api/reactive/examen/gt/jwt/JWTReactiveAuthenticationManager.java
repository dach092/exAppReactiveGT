package edu.api.reactive.examen.gt.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class JWTReactiveAuthenticationManager implements ReactiveAuthenticationManager {

	@Autowired
	private ReactiveUserDetailsService reactiveUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {

		if (authentication.isAuthenticated()) {
			return Mono.just(authentication);
		}
		return Mono.just(authentication).switchIfEmpty(Mono.defer(this::raiseBadCredentials))
				.cast(UsernamePasswordAuthenticationToken.class).flatMap(this::authenticateToken)
				.publishOn(Schedulers.parallel()).onErrorResume(e -> raiseBadCredentials())
				.filter(u -> passwordEncoder.matches((String) authentication.getCredentials(), u.getPassword()))
				.switchIfEmpty(Mono.defer(this::raiseBadCredentials))
				.map(u -> new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
						authentication.getCredentials(), u.getAuthorities()));
	}

	private <T> Mono<T> raiseBadCredentials() {
		return Mono.error(new BadCredentialsException("Usuario y/o clave incorrecta"));
	}

	private Mono<UserDetails> authenticateToken(final UsernamePasswordAuthenticationToken authenticationToken) {

		String username = authenticationToken.getName();

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			return this.reactiveUserDetailsService.findByUsername(username);
		}

		return null;
	}
}
