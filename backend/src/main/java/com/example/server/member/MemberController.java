package com.example.server.member;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.model.MemberVO;

@RestController
@RequestMapping("/member")
public class MemberController {

	MemberService memberService;
	
	@PostMapping("/join")
	public void join(MemberVO memberVO) {
		memberService.join(memberVO);		
	}
	
	@PostMapping("/findId")
	public String findId(MemberVO memberVO) {
		return memberService.findId(memberVO);		
	}
	
	@PostMapping("/findPassword")
	public String findPassword(MemberVO memberVO) {
		return memberService.findPassword(memberVO);		
	}

	@PostMapping("/myPage")
	public MemberVO myPage(MemberVO memberVO) {
		return memberService.myPage(memberVO).get();
	}
	
	@PutMapping("/changeMember")
	public void update(MemberVO memberVO) {
		memberService.update(memberVO);
	}
	@DeleteMapping("/quit")
	public MemberVO quit(MemberVO memberVO) {
		return memberService.delete(memberVO).get();
	}
	
}
