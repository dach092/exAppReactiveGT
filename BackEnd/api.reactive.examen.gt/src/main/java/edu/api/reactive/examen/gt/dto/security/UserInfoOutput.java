package edu.api.reactive.examen.gt.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoOutput {

	private String lastname;
	private String firstname;
}
