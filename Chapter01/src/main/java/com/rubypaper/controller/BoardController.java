package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}

//	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	@GetMapping("/hello.do")
	public String hello(String name) {
		return "Hello : " + name;
	}
	
    @GetMapping("/getBoard.do")
    public BoardVO getBoard() {
        BoardVO board = new BoardVO();
        board.setSeq(1);
        board.setTitle("임시 제목");
        board.setWriter("테스터");
        board.setContent("임시 내용............");        
        return board;
    }
    
    @GetMapping("/getBoardList.do")
    public List<BoardVO> getBoardList() {
    	List<BoardVO> listVO = new ArrayList<BoardVO>();
    	BoardVO board = null;
    	
        board = new BoardVO();
        board.setSeq(1);
        board.setTitle("임시 제목");
        board.setWriter("테스터");
        board.setContent("임시 내용............");    
        listVO.add(board);
        
        board = new BoardVO();
        board.setSeq(2);
        board.setTitle("임시 제목2");
        board.setWriter("테스터2");
        board.setContent("임시 내용.2222...........");    
        listVO.add(board);
        
        return listVO;
    }
}
