package edu.api.reactive.examen.gt.repository.manager;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import edu.api.reactive.examen.gt.document.manager.Inscription;
import edu.api.reactive.examen.gt.document.training.Student;
import reactor.core.publisher.Flux;

@Repository
public interface InscriptionRepository extends ReactiveMongoRepository<Inscription, String> {

	Flux<Inscription> findBySituationcode(Long Code);
	
	Flux<Inscription> findByNameLike(String Name);
}
