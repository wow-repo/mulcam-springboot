package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "boardList") //슨환참조 방지
public class Member {
	@Id
	private String id;
	private String password;
	private String name;
	private String role;
	
	// 연관관계의 주인이 아님을 선언한다.(mappedBy)
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
}
