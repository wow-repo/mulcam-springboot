package com.rubypaper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rubypaper.persistence.BoardDAO;

@Service("boardService")
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;

	public BoardService() {
		System.out.println("===> BoardService 생성");
	}
}
