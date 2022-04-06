package com.rubypaper.persistence;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	public BoardDAO() {
		System.out.println("===> BoardDAO 생성");
	}
}
