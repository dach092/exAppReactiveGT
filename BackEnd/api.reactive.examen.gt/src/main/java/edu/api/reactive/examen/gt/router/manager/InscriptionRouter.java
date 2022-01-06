package edu.api.reactive.examen.gt.router.manager;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;

import static edu.api.reactive.examen.gt.constants.Constants.*;
import static edu.api.reactive.examen.gt.constants.HttpResponse.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.api.reactive.examen.gt.document.manager.Inscription;
import edu.api.reactive.examen.gt.handler.manager.InscriptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class InscriptionRouter {

	@RouterOperations({

			@RouterOperation(path = API_ROUTE_INSCRIPTION, method = RequestMethod.GET, beanClass = InscriptionHandler.class, beanMethod = "findAll", operation = @Operation(operationId = "findAll", responses = {
					@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Inscription.class))),
					@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "List of all Inscriptions", summary = "List of all Inscriptions")),

			@RouterOperation(path = API_ROUTE_INSCRIPTION
					+ "/{id}", method = RequestMethod.GET, beanClass = InscriptionHandler.class, beanMethod = "findById", operation = @Operation(operationId = "findById", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Inscription") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Inscription.class))),
									@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "Search Inscription By Id", summary = "Search Inscription By Id")),

			@RouterOperation(path = API_ROUTE_INSCRIPTION
					+ "/byname/{name}", method = RequestMethod.GET, beanClass = InscriptionHandler.class, beanMethod = "findByNameLike", operation = @Operation(operationId = "findByNameLike", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "name", description = "name of Inscription") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Inscription.class))),
									@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "Search Inscription By Name", summary = "Search Inscription By Name")),

			@RouterOperation(path = API_ROUTE_INSCRIPTION
					+ "/bysituationcode/{situationcode}", method = RequestMethod.GET, beanClass = InscriptionHandler.class, beanMethod = "findBySituationcode", operation = @Operation(operationId = "findBySituationcode", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "situationcode", description = "Situation Code of Inscription") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Inscription.class))),
									@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "Search Inscription By Situation Code", summary = "Search Inscription By Situation Code")),

			@RouterOperation(path = API_ROUTE_INSCRIPTION, method = RequestMethod.POST, beanClass = InscriptionHandler.class, beanMethod = "add", operation = @Operation(operationId = "add", responses = {
					@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Inscription.class))),
					@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Inscription.class))), description = "Add Inscription", summary = "Add Inscription")),

			@RouterOperation(path = API_ROUTE_INSCRIPTION
					+ "/{id}", method = RequestMethod.PUT, beanClass = InscriptionHandler.class, beanMethod = "update", operation = @Operation(operationId = "update", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Inscription") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Inscription.class))),
									@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Inscription.class))), description = "Update Inscription", summary = "Update Inscription")),

			@RouterOperation(path = API_ROUTE_INSCRIPTION
					+ "/{id}", method = RequestMethod.DELETE, beanClass = InscriptionHandler.class, beanMethod = "delete", operation = @Operation(operationId = "delete", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Inscription") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Inscription.class))),
									@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, description = "Delete Inscription", summary = "Delete Inscription")) })

	@Bean
	public RouterFunction<ServerResponse> routeInscription(InscriptionHandler inscriptionHandler) {
		return RouterFunctions
				.route(GET(API_ROUTE_INSCRIPTION).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						inscriptionHandler::findAll)
				.andRoute(
						GET(API_ROUTE_INSCRIPTION + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						inscriptionHandler::findById)
				.andRoute(
						GET(API_ROUTE_INSCRIPTION + "/byname/{name}")
								.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						inscriptionHandler::findByNameLike)
				.andRoute(
						GET(API_ROUTE_INSCRIPTION + "/bysituationcode/{situationcode}")
								.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						inscriptionHandler::findBySituationcode)
				.andRoute(POST(API_ROUTE_INSCRIPTION).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						inscriptionHandler::add)
				.andRoute(
						PUT(API_ROUTE_INSCRIPTION + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						inscriptionHandler::update)
				.andRoute(DELETE(API_ROUTE_INSCRIPTION + "/{id}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), inscriptionHandler::delete);
	}
}
