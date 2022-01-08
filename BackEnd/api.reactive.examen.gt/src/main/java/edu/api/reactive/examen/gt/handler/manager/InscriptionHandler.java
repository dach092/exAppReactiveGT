package edu.api.reactive.examen.gt.handler.manager;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import edu.api.reactive.examen.gt.document.manager.Inscription;
import edu.api.reactive.examen.gt.document.manager.Situation;
import edu.api.reactive.examen.gt.service.manager.InscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Tag(name = "Inscription Resource", description = "API of Inscription")
public class InscriptionHandler {

	@Autowired
	private InscriptionService inscriptionService;

	@Autowired
	private JsonMapper jsonMapper;

	private Inscription getInscription(Inscription inscription) {
		return jsonMapper.convertValue(inscription, Inscription.class);
	}

	public Mono<ServerResponse> findAll(ServerRequest request) {

		Flux<Inscription> inscriptions = inscriptionService.findByState(0);
		return ok().contentType(MediaType.APPLICATION_JSON).body(inscriptions, Inscription.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");

		Mono<Inscription> inscription = inscriptionService.findById(id);
		return ok().body(inscription, Inscription.class);
	}

	public Mono<ServerResponse> findByNameLike(ServerRequest request) {

		String name = request.pathVariable("name");

		Flux<Inscription> inscriptions = inscriptionService.findByLike(Inscription.builder().name(name).build());
		return ok().body(inscriptions, Inscription.class);
	}

	public Mono<ServerResponse> findBySituationcode(ServerRequest request) {

		String situacionCode = request.pathVariable("situationcode");

		Flux<Inscription> inscriptions = inscriptionService
				.findBySituationcode(Inscription.builder().situationcode(situacionCode).build());

		return ok().body(inscriptions, Inscription.class);
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		Mono<Inscription> inscription = request.bodyToMono(Inscription.class);
		return status(HttpStatus.CREATED)
				.body(fromPublisher(inscription.flatMap(inscriptionService::save), Inscription.class));
	}

	public Mono<ServerResponse> update(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Inscription> inscription = request.bodyToMono(Inscription.class);

		return inscriptionService.findById(id).flatMap(inscription01 -> inscription.flatMap(inscription02 -> {

			inscription02.setId(id);
			Mono<Inscription> updateInscription = inscriptionService.save(this.getInscription(inscription02));

			return ok().body(updateInscription, Inscription.class);
		}));
	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");

		return inscriptionService.findById(id).flatMap(inscription -> {
			inscription.setState(1);
			Mono<Inscription> updateInscription = inscriptionService.save(inscription);

			return ok().body(updateInscription, Inscription.class);
		});
	}
}
