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
public class Instructor {

	@Field(name="code")
	private String code;
	
	@Field(name="firstname")
	private String firstname;
	
	@Field(name="lastname")
	private String lastname;
	
	@Field(name="age")
	private Integer age;
	
	@Field(name="address")
	private String address;
	
	@Field(name="email")
	private String email;
	
	@Field(name="speciality")
	private Speciality speciality;
}
