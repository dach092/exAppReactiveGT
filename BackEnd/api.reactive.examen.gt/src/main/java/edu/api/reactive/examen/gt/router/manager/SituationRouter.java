package edu.api.reactive.examen.gt.router.manager;

import static edu.api.reactive.examen.gt.constants.Constants.*;
import static edu.api.reactive.examen.gt.constants.HttpResponse.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.api.reactive.examen.gt.document.manager.Situation;
import edu.api.reactive.examen.gt.handler.manager.SituationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class SituationRouter {

	@RouterOperations({

			@RouterOperation(path = API_ROUTE_SITUATION, method = RequestMethod.GET, beanClass = SituationHandler.class, beanMethod = "findAll", operation = @Operation(operationId = "findAll", responses = {
					@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Situation.class))),
					@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "List of all Situations", summary = "List of all Situations")),

			@RouterOperation(path = API_ROUTE_SITUATION
					+ "/{id}", method = RequestMethod.GET, beanClass = SituationHandler.class, beanMethod = "findById", operation = @Operation(operationId = "findById", responses = {
							@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Situation.class))),
							@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "List of all Situations", summary = "List of all Situations")),

			@RouterOperation(path = API_ROUTE_SITUATION, method = RequestMethod.POST, beanClass = SituationHandler.class, beanMethod = "add", operation = @Operation(operationId = "add", responses = {
					@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Situation.class))),
					@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Situation.class))), description = "Add Situation", summary = "Add Situation")),

			@RouterOperation(path = API_ROUTE_SITUATION
					+ "/{id}", method = RequestMethod.PUT, beanClass = SituationHandler.class, beanMethod = "update", operation = @Operation(operationId = "update", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Situation") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Situation.class))),
									@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Situation.class))), description = "Update Situation", summary = "Update Situation")),

			@RouterOperation(path = API_ROUTE_SITUATION
					+ "/{id}", method = RequestMethod.DELETE, beanClass = SituationHandler.class, beanMethod = "delete", operation = @Operation(operationId = "delete", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Situation") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Situation.class))),
									@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, description = "Delete Situation", summary = "Delete Situation")) })

	@Bean
	public RouterFunction<ServerResponse> routeSituation(SituationHandler situationHandler) {
		return RouterFunctions
				.route(GET(API_ROUTE_SITUATION).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						situationHandler::findAll)
				.andRoute(GET(API_ROUTE_SITUATION + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						situationHandler::findById)
				.andRoute(POST(API_ROUTE_SITUATION).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						situationHandler::add)
				.andRoute(PUT(API_ROUTE_SITUATION + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						situationHandler::update)
				.andRoute(
						DELETE(API_ROUTE_SITUATION + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						situationHandler::delete);

	}
}
