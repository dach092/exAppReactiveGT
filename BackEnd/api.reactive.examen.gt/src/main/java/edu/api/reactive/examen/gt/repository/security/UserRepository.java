package edu.api.reactive.examen.gt.repository.security;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import edu.api.reactive.examen.gt.document.security.UserLocal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserLocal, String> {

	Mono<UserLocal> findByUsername(String username);
	
	Flux<UserLocal> findByUsernameLike(String username);
}
