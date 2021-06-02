package com.dev4.sunbbang.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.MemberVO;
import com.google.gson.Gson;

@Async
@RestController
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	Gson gson;
	
	@PostMapping("/member/join")
	public Object join(@RequestBody MemberVO memberVO) {
		
		return gson.toJson(memberVO);
		
	}
	
//	@PostMapping("/findId")
//	public String findId(MemberVO memberVO) {
//		return memberService.findId(memberVO);		
//	}
//	
//	@PostMapping("/findPassword")
//	public String findPassword(MemberVO memberVO) {
//		return memberService.findPassword(memberVO);		
//	}
//
//	@PostMapping("/myPage")
//	public MemberVO myPage(MemberVO memberVO) {
//		return memberService.myPage(memberVO).get();
//	}
//	
//	@PutMapping("/changeMember")
//	public void update(MemberVO memberVO) {
//		memberService.update(memberVO);
//	}
//	@DeleteMapping("/quit")
//	public MemberVO quit(MemberVO memberVO) {
//		return memberService.delete(memberVO).get();
//	}
	
	@PostMapping("/member/login")
	public Object login(@RequestBody MemberVO memberVo) {
		
		AuthVO avo = memberService.login(memberVo).get();
		
						
		return gson.toJson(avo);
		
	}
	
	
}
