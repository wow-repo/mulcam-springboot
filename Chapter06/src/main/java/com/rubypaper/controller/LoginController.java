package com.rubypaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.rubypaper.domain.Member;
import com.rubypaper.service.MemberService;

// @SessionAttributes : Model에 "member"라는 이름으로 검색 결과가 등록되는 순간 세션에도 등록해라.
@SessionAttributes("member")
@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String loginView() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		// 회원 정보를 검색한다. (PK값만 이용해서 검색한다.)
		Member findMember = memberService.getMember(member);
		
		// 비밀번호 검증
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			// 아이디와 비밀번호 검증이 성공하면 글 목록 화면으로 이동한다.
			model.addAttribute("member", findMember);
			return "redirect:getBoardList";
		} else {
			// 로그인 실패인 경우 다시 로그인 화면을 제공한다.
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}
}
