package com.example.st2i.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocResponse {
	private long id;
	private Personne eleve;
	private DocumentAdministratif document;
}
