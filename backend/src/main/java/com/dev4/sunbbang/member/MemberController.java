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
	public boolean join(@RequestBody MemberVO memberVO) {
		try {
			memberService.join(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	@PostMapping("/member/login")
//	public Object login(@RequestBody MemberVO memberVO) {
//		return gson.toJson(memberService.login(memberVO).get());
//	}

	@PostMapping("/member/findId")
	public Object findId(@RequestBody MemberVO memberVO) {
		String foundId = memberService.findId(memberVO);
		return gson.toJson(foundId);
	}

	@PostMapping("/member/findPassword")
	public boolean findPassword(@RequestBody MemberVO memberVO) {
		return memberService.findPassword(memberVO);
	}

	@PostMapping("/member/changePassword")
	public boolean changePassword(@RequestBody MemberVO memberVO) {
		try {
			memberService.changePassword(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/member/myPage")
	public Object myPage(@RequestBody MemberVO memberVO) {
		MemberVO mvo = memberService.myPage(memberVO).get();
		return gson.toJson(mvo);
	}

	@PostMapping("/member/changeMember")
	public boolean changeMember(@RequestBody MemberVO memberVO) {
		try {
			memberService.changeMember(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/member/quit")
	public boolean quit(@RequestBody MemberVO memberVO) {
		try {
			memberService.quit(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
