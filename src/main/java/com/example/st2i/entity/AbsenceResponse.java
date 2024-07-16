package com.example.st2i.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceResponse {
	private long id;
	private Personne eleve;
	private Personne enseignant;
	private Absence absence;
}
