package edu.api.reactive.examen.gt.dto.security;

import java.util.List;

import edu.api.reactive.examen.gt.document.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLocalDto {

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private Integer state;
	private List<Role> roles;
}
