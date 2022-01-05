package edu.api.reactive.examen.gt.document.manager;

import java.io.Serializable;
import java.util.Date;
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
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Inscription")
public class Inscription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	@Field(name = "code")
	private Long code;

	@Field(name = "name")
	private String name;

	@Field(name = "description")
	private String description;

	@Field(name = "startdate")
	private Date startdate;

	@Field(name = "numberrecord")
	private Long numberrecord;

	@Field(name = "instructor")
	private Instructor instructor;

	@Field(name = "situationcode")
	private Long situationcode;

	@Field(name = "state")
	private Integer state;
}