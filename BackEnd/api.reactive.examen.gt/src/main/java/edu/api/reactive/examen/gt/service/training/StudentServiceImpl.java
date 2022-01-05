package edu.api.reactive.examen.gt.service.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.api.reactive.examen.gt.document.training.Student;
import edu.api.reactive.examen.gt.repository.training.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Flux<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Mono<Student> save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Mono<Student> findById(String id) {
		return studentRepository.findById(id);
	}

	@Override
	public Flux<Student> findByLike(Student student) {
		return studentRepository.findByLastnameLike(student.getLastname());
	}
}
