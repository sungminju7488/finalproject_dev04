package com.dev4.sunbbang.member;

import javax.validation.Valid;

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
	public void join(@Valid@RequestBody MemberVO memberVO) {
		memberService.join(memberVO);
	}

	@PostMapping("/member/findId")
	public Object findId(@RequestBody MemberVO memberVO) {
		String mvo = memberService.findId(memberVO);
		return gson.toJson(mvo);
	}

	@PostMapping("/member/confirmPassword")
	public void confirmPassword(@RequestBody MemberVO memberVO) {
		 memberService.confirmPassword(memberVO);
	}
	@PostMapping("/member/changePassword")
	public void changePassword(@Valid@RequestBody MemberVO memberVO) {
		memberService.changePassword(memberVO);
	}

	@PostMapping("/member/myPage")
	public Object myPage(@RequestBody MemberVO memberVO) {
		MemberVO mvo = memberService.myPage(memberVO).get();
		return gson.toJson(mvo);
	}

	@PutMapping("/member/changeMember")
	public void update(@Valid@RequestBody MemberVO memberVO) {
		memberService.update(memberVO);
	}

	@DeleteMapping("/quit")
	public void quit(@RequestBody MemberVO memberVO) {
		memberService.delete(memberVO);
	}

}
