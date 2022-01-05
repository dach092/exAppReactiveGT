package edu.api.reactive.examen.gt.service.manager;

import edu.api.reactive.examen.gt.document.manager.Inscription;
import edu.api.reactive.examen.gt.service.generic.GenericService;
import reactor.core.publisher.Flux;

public interface InscriptionService extends GenericService<Inscription> {

	Flux<Inscription> findBySituationcode(Inscription inscription);
}
