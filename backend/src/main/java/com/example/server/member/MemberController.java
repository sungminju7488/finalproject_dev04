package com.example.server.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.model.MemberVO;

@RestController
public class MemberController {

	MemberService memberService;
	
	@PostMapping("/member/join")
	public void join(MemberVO memberVO) {
		memberService.join(memberVO);		
	}
	
	@PostMapping("/member/myPage")
	public MemberVO myPage(MemberVO memberVO) {
		return memberService.myPage(memberVO).get();
	}
	
	@PostMapping("/member/changeMember")
	public void update(MemberVO memberVO) {
		memberService.update(memberVO);
	}
//	@PostMapping("/member/quit")
//	public MemberVO quit(MemberVO memberVO) {
//		
//		return memberService.delete(memberVO).get();
//	}
	
}
