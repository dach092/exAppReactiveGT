package edu.api.reactive.examen.gt.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import static edu.api.reactive.examen.gt.constants.JWTConstants.*;

@Slf4j
@Component
public class JWTTokenProvider {

	private final Base64.Encoder encoder = Base64.getEncoder();
	private String secretKey;

	private boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@PostConstruct
	public void init() {
		this.secretKey = encoder.encodeToString(SALT_KEY.getBytes(StandardCharsets.UTF_8));
	}

	public String getToken(Authentication authentication) {

		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		Date validity = new Date((new Date()).getTime() + TOKEN_VALIDITY);

		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(String token) {

		if ((token == null) || !validateToken(token)) {
			throw new BadCredentialsException("Token inválido");
		}

		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
}
