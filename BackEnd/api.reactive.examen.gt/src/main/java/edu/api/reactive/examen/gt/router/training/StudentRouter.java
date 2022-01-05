package edu.api.reactive.examen.gt.router.training;

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

import edu.api.reactive.examen.gt.document.training.Student;
import edu.api.reactive.examen.gt.handler.training.StudentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class StudentRouter {

	@RouterOperations({

			@RouterOperation(path = API_ROUTE_STUDENTS, method = RequestMethod.GET, beanClass = StudentHandler.class, beanMethod = "findAll", operation = @Operation(operationId = "findAll", responses = {
					@ApiResponse(responseCode = HTTP_CODE_200, description = HTTP_MSG_200, content = @Content(schema = @Schema(implementation = Student.class))),
					@ApiResponse(responseCode = HTTP_CODE_204, description = HTTP_MSG_204) }, description = "List of all Students includ in areas", summary = "List of all Students")),

			@RouterOperation(path = API_ROUTE_STUDENTS, method = RequestMethod.POST, beanClass = StudentHandler.class, beanMethod = "add", operation = @Operation(operationId = "add", responses = {
					@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Student.class))),
					@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Student.class))), description = "Add Student", summary = "Add Student")),

			@RouterOperation(path = API_ROUTE_STUDENTS
					+ "/{id}", method = RequestMethod.PUT, beanClass = StudentHandler.class, beanMethod = "update", operation = @Operation(operationId = "update", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Student") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Student.class))),
									@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Student.class))), description = "Update Student", summary = "Update Student")),

			@RouterOperation(path = API_ROUTE_STUDENTS
					+ "/{id}", method = RequestMethod.DELETE, beanClass = StudentHandler.class, beanMethod = "delete", operation = @Operation(operationId = "delete", parameters = {
							@Parameter(in = ParameterIn.PATH, name = "id", description = "id of Student") }, responses = {
									@ApiResponse(responseCode = HTTP_CODE_201, description = HTTP_MSG_201, content = @Content(schema = @Schema(implementation = Student.class))),
									@ApiResponse(responseCode = HTTP_CODE_400, description = HTTP_MSG_400) }, description = "Delete Student", summary = "Delete Student")) })
	@Bean
	public RouterFunction<ServerResponse> routeStudent(StudentHandler studentHandler) {
		return RouterFunctions
				.route(GET(API_ROUTE_STUDENTS).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						studentHandler::findAll)
				.andRoute(POST(API_ROUTE_STUDENTS).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						studentHandler::add)
				.andRoute(PUT(API_ROUTE_STUDENTS + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						studentHandler::update)
				.andRoute(
						DELETE(API_ROUTE_STUDENTS + "/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						studentHandler::delete);

	}

}
