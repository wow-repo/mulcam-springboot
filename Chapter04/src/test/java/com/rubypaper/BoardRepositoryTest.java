package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepository;
	
//	@Test
	public void insertBoardTest() {
		// EntityManager 획독이나 Transaction 관련 작업은 BoardRepository가 자동으로 처리해준다.
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스터");
		board.setContent("글 등록 테스트합니다..");
		boardRepository.save(board); // save() == persist()
	}
	
	@Test
	public void updateBoardTest() {
		Board board = new Board();
		board.setSeq(1L);
		board.setWriter("테스터");
		board.setTitle("---수정 제목");
		board.setContent("---수정 내용...");
		
		boardRepository.save(board); // save() == merge()
	}
	
//	@Test
	public void gettBoardTest() {
		Board board = boardRepository.findById(1L).get();
		System.out.println("검색된 게시글 : " + board.toString());
	}
	
    @Test
    public void testDeleteBoard() {
        boardRepository.deleteById(1L);
    }
}
