package edu.api.reactive.examen.gt.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.api.reactive.examen.gt.document.manager.Inscription;
import edu.api.reactive.examen.gt.repository.manager.InscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class InscriptionServiceImpl implements InscriptionService {

	@Autowired
	private InscriptionRepository inscriptionRepository;

	@Override
	public Flux<Inscription> findAll() {
		return inscriptionRepository.findAll();
	}

	@Override
	public Mono<Inscription> save(Inscription inscription) {
		return inscriptionRepository.save(inscription);
	}

	@Override
	public Mono<Inscription> findById(String id) {
		return inscriptionRepository.findById(id);
	}

	@Override
	public Flux<Inscription> findByLike(Inscription inscription) {
		return inscriptionRepository.findByNameLike(inscription.getName());
	}

	@Override
	public Flux<Inscription> findBySituationcode(Inscription inscription) {
		return inscriptionRepository.findBySituationcode(inscription.getSituationcode());
	}
}
