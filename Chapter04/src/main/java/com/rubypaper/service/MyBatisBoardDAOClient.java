package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.mapper.MyBatisBoardDAO;

//@Service
public class MyBatisBoardDAOClient implements ApplicationRunner {
	@Autowired
	private MyBatisBoardDAO boardDAO;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Board board = new Board();
		board.setTitle("MyBatis 테스트");
		board.setWriter("테스터");
		board.setContent("MyBatis 테스트 중입니다..");
		
		boardDAO.insertBoard(board);
		
		List<Board> boardList  = boardDAO.getBoardList();
		for (Board row : boardList) {
			System.out.println("--->" + row.toString());
		}
	}
}