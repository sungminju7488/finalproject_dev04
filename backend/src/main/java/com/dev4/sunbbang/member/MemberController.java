package com.dev4.sunbbang.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public void join(@RequestBody MemberVO memberVO) {
		memberService.join(memberVO);
	}

	@PostMapping("/findId")
	public Object findId(MemberVO memberVO) {
		String mvo = memberService.findId(memberVO);
		return gson.toJson(mvo);
	}

//	@PostMapping("/findPassword")
//	public String findPassword(MemberVO memberVO) {
//		return memberService.findPassword(memberVO);
//	}

	@PostMapping("/myPage")
	public Object myPage(MemberVO memberVO) {
		MemberVO mvo = memberService.myPage(memberVO).get();
		return gson.toJson(mvo);
	}

	@PutMapping("/changeMember")
	public void update(MemberVO memberVO) {
		memberService.update(memberVO);
	}

	@DeleteMapping("/quit")
	public void quit(MemberVO memberVO) {
		memberService.delete(memberVO);
	}

}
