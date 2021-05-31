package com.dev4.sunbbang.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.MemberVO;
import com.google.gson.Gson;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	Gson gson;
	
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		System.out.println(memberVO.getMemberId());
		return gson.toJson(memberVO);
//		memberService.join(memberVO);
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
	
}
