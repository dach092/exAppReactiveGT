package edu.api.reactive.examen.gt.handler.training;

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

import edu.api.reactive.examen.gt.document.training.Student;
import edu.api.reactive.examen.gt.service.training.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Tag(name = "Student Resource", description = "API of Students")
public class StudentHandler {

	@Autowired
	private StudentService studentService;

	@Autowired
	private JsonMapper jsonMapper;

	public Mono<ServerResponse> findAll(ServerRequest request) {

		Flux<Student> students = studentService.findByState(0);
		return ok().contentType(MediaType.APPLICATION_JSON).body(students, Student.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {

		String id = request.pathVariable("id");

		Mono<Student> students = studentService.findById(id);
		return ok().contentType(MediaType.APPLICATION_JSON).body(students, Student.class);
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		Mono<Student> student = request.bodyToMono(Student.class);
		return status(HttpStatus.CREATED).body(fromPublisher(student.flatMap(studentService::save), Student.class));
	}

	public Mono<ServerResponse> update(ServerRequest request) {

		String id = request.pathVariable("id");
		Mono<Student> student = request.bodyToMono(Student.class);

		return studentService.findById(id).flatMap(student01 -> student.flatMap(student02 -> {
			student02.setId(id);
			Mono<Student> updatedStudent = studentService.save(this.getStudent(student02));

			return ok().body(updatedStudent, Student.class);
		}));
	}

	public Mono<ServerResponse> delete(ServerRequest request) {

		String id = request.pathVariable("id");
		return studentService.findById(id).flatMap(student -> {
			student.setState(1);
			Mono<Student> updatedStudent = studentService.save(student);
			return ok().body(updatedStudent, Student.class);
		});
	}

	private Student getStudent(Student student) {
		return jsonMapper.convertValue(student, Student.class);
	}
}
