package com.example.st2i.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Permission;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
	ETUDIANT,
	PARENT,
	ENSEIGNANT,
	ADMINISTRATEUR,
	PERSONNEL


}