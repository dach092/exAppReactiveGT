package edu.api.reactive.examen.gt.service.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<T> {

	Flux<T> findAll();

	Mono<T> save(T t);

	Mono<T> findById(String id);

	Flux<T> findByLike(T t);
}
