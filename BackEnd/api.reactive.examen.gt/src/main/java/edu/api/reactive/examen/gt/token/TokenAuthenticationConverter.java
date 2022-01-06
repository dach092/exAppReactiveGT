package edu.api.reactive.examen.gt.token;

import static edu.api.reactive.examen.gt.constants.JWTConstants.*;

import java.util.Objects;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import edu.api.reactive.examen.gt.jwt.JWTTokenProvider;
import edu.api.reactive.examen.gt.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class TokenAuthenticationConverter implements ServerAuthenticationConverter{

	private static final Function<String, String> value = authValue -> authValue.substring(BEARER.length(), authValue.length());
	private final JWTTokenProvider jWTTokenProvider;
	
	public TokenAuthenticationConverter(JWTTokenProvider jWTTokenProvider) {
		this.jWTTokenProvider = jWTTokenProvider;
	}

	@Override
	public Mono<Authentication> convert(ServerWebExchange serverWebExchange) {
		
		return  Mono.justOrEmpty(serverWebExchange)
						.map(SecurityUtil::getTokenFromRequest)
						.map(value)						
						.map(jWTTokenProvider::getAuthentication)
						.filter(Objects::nonNull);
	}
}
