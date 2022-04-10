package com.rubypaper.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private String name;
	private boolean enabled;
}
