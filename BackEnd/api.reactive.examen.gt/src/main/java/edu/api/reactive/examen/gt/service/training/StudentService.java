package edu.api.reactive.examen.gt.service.training;

import edu.api.reactive.examen.gt.document.training.Student;
import edu.api.reactive.examen.gt.service.generic.GenericService;
import reactor.core.publisher.Flux;

public interface StudentService extends GenericService<Student> {
	Flux<Student> findByState(Integer state);
}
