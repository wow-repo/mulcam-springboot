package com.rubypaper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rubypaper.domain.Board;

@Mapper
public interface BoardMapper {
	@Insert("insert into board(seq, title, writer, content) values ((select max(seq)+1 from board), #{title}, #{writer}, #{content})")
	public void insertBoard(Board board);
	
    @Select("select * from board order by seq desc")
    public List<Board> getBoardList();
}
