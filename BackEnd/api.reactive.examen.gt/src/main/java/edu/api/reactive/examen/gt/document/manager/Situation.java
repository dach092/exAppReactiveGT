package edu.api.reactive.examen.gt.document.manager;

import java.io.Serializable;
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
@Document(collection = "Situation")
public class Situation implements Serializable {

	private static final long serialVersionUID = 6121913723918038462L;

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	@Field(name = "code")
	private String code;

	@Field(name = "name")
	private String name;

	@Field(name = "state")
	private Integer state;
}
