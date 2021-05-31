package com.dev4.sunbbang.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.MemberVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jdk.nashorn.internal.runtime.JSONFunctions;

@RestController
//@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	Gson gson;
	
	@Async
	@PostMapping("/member/join")
	public String join(@RequestBody MemberVO memberVO) {
		
		System.out.println(memberVO.toString());
		return memberVO.toString();
//		memberService.join(memberVO);
	}
//	@GetMapping("/member/join")
//	public String getjoin() {
//		System.out.println("Get 통신에 성공했습니다.");
//		return "가입 리턴이 정상작동합니다";
//	}
	
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
	
}
