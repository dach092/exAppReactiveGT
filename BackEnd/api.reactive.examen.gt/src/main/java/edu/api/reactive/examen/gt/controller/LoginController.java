package edu.api.reactive.examen.gt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import edu.api.reactive.examen.gt.document.security.UserLocal;
import edu.api.reactive.examen.gt.dto.security.UserDto;
import edu.api.reactive.examen.gt.dto.security.UserInfoOutput;
import edu.api.reactive.examen.gt.jwt.JWTReactiveAuthenticationManager;
import edu.api.reactive.examen.gt.jwt.JWTTokenProvider;
import edu.api.reactive.examen.gt.service.security.UserService;

import static edu.api.reactive.examen.gt.constants.JWTConstants.*;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(LOGIN_URL)
public class LoginController {

	@Autowired
	private JWTTokenProvider jWTTokenProvider;

	@Autowired
	private JWTReactiveAuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping
	public Mono<ResponseEntity<?>> authorize(@RequestBody UserDto userDto) {

		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(),
				userDto.getPassword());

		Mono<Authentication> authentication = this.authenticationManager.authenticate(authenticationToken);

		authentication.doOnError(throwable -> {
			throw new BadCredentialsException("Usuario y/o clave incorrecta");
		});

		ReactiveSecurityContextHolder.withAuthentication(authenticationToken);

		Mono<UserInfoOutput> userInfo = userService.findByUsername(userDto.getUsername());

		return authentication.map(auth -> {

			String jwt = jWTTokenProvider.getToken(auth);

			//build se usa cuando no hay body			
			return ResponseEntity.ok().header("Access-Control-Expose-Headers", "Authorization")
					.header("Authorization", BEARER + jwt).body(userInfo);
		});
	}
}
