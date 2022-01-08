package edu.api.reactive.examen.gt.service.security;

import edu.api.reactive.examen.gt.document.security.UserLocal;
import edu.api.reactive.examen.gt.dto.security.UserInfoOutput;
import edu.api.reactive.examen.gt.service.generic.GenericService;
import reactor.core.publisher.Mono;

public interface UserService extends GenericService<UserLocal> {
	
	Mono<UserInfoOutput> findByUsername(String username);
}
