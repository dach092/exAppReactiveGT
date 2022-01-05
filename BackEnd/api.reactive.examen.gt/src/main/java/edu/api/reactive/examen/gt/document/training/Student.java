package edu.api.reactive.examen.gt.document.training;

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
@Document(collection = "Student")
public class Student implements Serializable {

	private static final long serialVersionUID = -1278120354126389856L;

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	@Field(name = "firstname")
	private String firstname;

	@Field(name = "lastname")
	private String lastname;

	@Field(name = "age")
	private Integer age;

	@Field(name = "address")
	private String address;

	@Field(name = "email")
	private String email;
	
	@Field(name = "state")
	private Integer state;

	@Field(name = "inscriptioncodes")
	private List<Long> inscriptioncodes;
}
