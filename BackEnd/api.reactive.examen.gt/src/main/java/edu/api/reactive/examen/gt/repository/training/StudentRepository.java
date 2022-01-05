package edu.api.reactive.examen.gt.repository.training;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import edu.api.reactive.examen.gt.document.training.Student;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

	Flux<Student> findByLastnameLike(String lastname);
}
