package edu.api.reactive.examen.gt.document.security;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User implements Serializable {
	private static final long serialVersionUID = 8169725800250795924L;

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	@Field(name = "username")
	private String username;

	@Field(name = "password")
	private String password;

	@Field(name = "firstname")
	private String firstname;

	@Field(name = "lastname")
	private String lastname;

	@Field(name = "state")
	private Boolean state;

	@Field(name = "roles")
	private List<Role> roles;
}
