package edu.api.reactive.examen.gt.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.api.reactive.examen.gt.document.security.UserLocal;
import edu.api.reactive.examen.gt.dto.security.UserInfoOutput;
import edu.api.reactive.examen.gt.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Flux<UserLocal> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<UserLocal> save(UserLocal userLocal) {
		return userRepository.save(userLocal);
	}

	@Override
	public Mono<UserLocal> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Flux<UserLocal> findByLike(UserLocal userLocal) {
		return userRepository.findByUsernameLike(userLocal.getUsername());
	}

	@Override
	public Mono<UserInfoOutput> findByUsername(String username) {
		return userRepository.findByUsername(username).map(this::toUserInfoOutput);
	}

	private UserInfoOutput toUserInfoOutput(UserLocal user) {
		return new UserInfoOutput(user.getLastname(), user.getFirstname());
	}
}
