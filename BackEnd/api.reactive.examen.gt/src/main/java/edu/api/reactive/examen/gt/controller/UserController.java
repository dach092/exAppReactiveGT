package edu.api.reactive.examen.gt.controller;

import static edu.api.reactive.examen.gt.constants.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.api.reactive.examen.gt.document.security.UserLocal;
import edu.api.reactive.examen.gt.dto.security.UserLocalDto;
import edu.api.reactive.examen.gt.service.security.UserService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(API_USER)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public Mono<ResponseEntity<?>> newUser(@RequestBody UserLocalDto userLocalDto) {

		UserLocal userLocal = new UserLocal(null, userLocalDto.getUsername(),
				new BCryptPasswordEncoder().encode(userLocalDto.getPassword()), userLocalDto.getFirstname(),
				userLocalDto.getLastname(), 0, userLocalDto.getRoles());

		Mono<UserLocal> monoUserLocal = userService.save(userLocal);

		return monoUserLocal.map(user -> {
			return ResponseEntity.ok().body(user);
		});
	}
}
