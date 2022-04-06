package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;
import com.rubypaper.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}

//	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	@GetMapping("/hello.do")
	public String hello(String name) {
		System.out.println("---> BoardController.hello() 실행");
		return boardService.hello(name);
	}
	
    @GetMapping("/getBoard.do")
    public Map<String, Object> getBoard(int seq) {
        return boardService.getBoard(seq);
    }
    
    @GetMapping("/getBoardList.do")
    public List<Map<String, Object>> getBoardList() {
    	return boardService.getBoardList();
    }
}
