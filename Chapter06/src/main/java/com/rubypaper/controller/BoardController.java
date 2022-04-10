package com.rubypaper.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
    @GetMapping("/getBoardList")
    public String getBoardList(Board board, Model model, HttpSession session) {
    	// 세션 체크
    	Member member  = (Member) session.getAttribute("member");
    	if (member == null) {
    		return "redirect:/index.html";
    	}
    	
    	// 검색한 글 목록을 Model에 저장하여 JSP 화면에서 사용할 수 있도록 한다.
    	model.addAttribute("boardList", boardService.getBoardList(board));
    	
        return "getBoardList"; // View 이름(JSP) 리턴
    }
    
    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
    	// 선택한 글 목록을 Model에 저장하여 JSP 화면에서 사용할 수 있도록 한다.
    	model.addAttribute("board", boardService.getBoard(board));
    	
        return "getBoard"; // View 이름(JSP) 리턴
    }
    
    @GetMapping("/insertBoard")
    public String insertBoardView() {
        System.out.println("---> GET 방식으로 글 등록 화면 요청 들어옴");
        return "insertBoard";
    }
    
    // 글 등록 처리
    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @RequestBody MultipartFile uploadFile) throws IllegalStateException, IOException {
    	// 파일 업로드 처리
    	String fileName = uploadFile.getOriginalFilename();
    	uploadFile.transferTo(new File("C:/DEV/upload_files/" + fileName));
    	
        System.out.println("---> POST 방식으로 글등록 요청 들어옴");
        boardService.insertBoard(board);
        return "redirect:getBoardList";
    }
    
    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("---> POST 방식으로 글수정 요청 들어옴");
        boardService.updateBoard(board);
        return "redirect:getBoardList";
    }
    
    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("---> POST 방식으로 글삭제 요청 들어옴");
        boardService.deleteBoard(board);
        return "redirect:getBoardList";
    }
}
