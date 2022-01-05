package edu.api.reactive.examen.gt.document.manager;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {

	@Field(name = "code")
	private Long code;

	@Field(name = "name")
	private String name;
}
