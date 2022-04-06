package com.rubypaper.mapper;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rubypaper.domain.Board;

@Repository
public class MyBatisBoardDAO {

	// MyBatis 컨테이너를 의존성 주입한다.
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertBoard(Board board) {
		mybatis.insert("insertBoard", board);
	}

	public List<Board> getBoardList() {
		return mybatis.selectList("getBoardList");
	}
}
