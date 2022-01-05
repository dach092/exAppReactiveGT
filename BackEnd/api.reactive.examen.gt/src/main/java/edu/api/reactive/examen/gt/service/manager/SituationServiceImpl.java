package edu.api.reactive.examen.gt.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.api.reactive.examen.gt.document.manager.Situation;
import edu.api.reactive.examen.gt.repository.manager.SituationRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SituationServiceImpl implements SituationService {

	@Autowired
	private SituationRepository situationRepository;

	@Override
	public Flux<Situation> findAll() {
		return situationRepository.findAll();
	}

	@Override
	public Mono<Situation> save(Situation situation) {
		return situationRepository.save(situation);
	}

	@Override
	public Mono<Situation> findById(String id) {
		return situationRepository.findById(id);
	}

	@Override
	public Flux<Situation> findByLike(Situation situation) {
		return situationRepository.findByNameLike(situation.getName());
	}

}
