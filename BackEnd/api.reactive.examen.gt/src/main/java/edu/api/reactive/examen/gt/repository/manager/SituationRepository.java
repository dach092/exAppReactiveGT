package edu.api.reactive.examen.gt.repository.manager;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import edu.api.reactive.examen.gt.document.manager.Situation;
import reactor.core.publisher.Flux;

@Repository
public interface SituationRepository extends ReactiveMongoRepository<Situation, String> {

	Flux<Situation> findByNameLike(String Name);

	Flux<Situation> findByState(Integer state);
}
