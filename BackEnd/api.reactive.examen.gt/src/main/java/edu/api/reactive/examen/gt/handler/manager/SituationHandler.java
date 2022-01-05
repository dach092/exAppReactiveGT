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

import edu.api.reactive.examen.gt.document.manager.Situation;
import edu.api.reactive.examen.gt.service.manager.SituationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Tag(name = "Situation Resource", description = "API of Situation")
public class SituationHandler {

	@Autowired
	private SituationService situationService;

	@Autowired
	private JsonMapper jsonMapper;

	private Situation getSituation(Situation situation) {
		return jsonMapper.convertValue(situation, Situation.class);
	}

	public Mono<ServerResponse> findAll(ServerRequest request) {

		Flux<Situation> situations = situationService.findAll();
		return ok().contentType(MediaType.APPLICATION_JSON).body(situations, Situation.class);
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		Mono<Situation> situation = request.bodyToMono(Situation.class);
		return status(HttpStatus.CREATED)
				.body(fromPublisher(situation.flatMap(situationService::save), Situation.class));
	}

	public Mono<ServerResponse> update(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Situation> situation = request.bodyToMono(Situation.class);

		return situationService.findById(id).flatMap(situation01 -> situation.flatMap(situation02 -> {

			situation02.setId(id);
			Mono<Situation> updateSituation = situationService.save(this.getSituation(situation02));

			return ok().body(updateSituation, Situation.class);
		}));
	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");

		return situationService.findById(id).flatMap(situation -> {
			situation.setState(1);
			Mono<Situation> updateSituation = situationService.save(situation);

			return ok().body(updateSituation, Situation.class);
		});
	}
}
