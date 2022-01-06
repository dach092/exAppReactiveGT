package edu.api.reactive.examen.gt.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.api.reactive.examen.gt.document.security.Role;
import edu.api.reactive.examen.gt.document.security.UserLocal;
import edu.api.reactive.examen.gt.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<UserDetails> findByUsername(String username) {

		return this.userRepository.findByUsername(username).switchIfEmpty(Mono.defer(() -> {
			return Mono.error(new UsernameNotFoundException("Usuario no existe"));
		})).map(this::toUserDetails);
	}

	public Collection<GrantedAuthority> getAuthorities(List<Role> roles) {

		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role rol : roles) {
			authorities.add(new SimpleGrantedAuthority(rol.getName()));
		}

		return authorities;
	}

	private UserDetails toUserDetails(UserLocal user) {
		return new User(user.getUsername(), user.getPassword(), this.getAuthorities(user.getRoles()));
	}

}
