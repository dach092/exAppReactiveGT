package edu.api.reactive.examen.gt.service.manager;

import edu.api.reactive.examen.gt.document.manager.Situation;
import edu.api.reactive.examen.gt.service.generic.GenericService;
import reactor.core.publisher.Flux;

public interface SituationService extends GenericService<Situation> {

	Flux<Situation> findByState(Integer state);
}
