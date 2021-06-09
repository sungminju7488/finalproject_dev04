package com.dev4.sunbbang.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.util.JwtService;
import com.dev4.sunbbang.util.ResponseToken;
import com.google.gson.Gson;

@Async
@RestController
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	Gson gson;
	
	@PostMapping("/member/join")
	public boolean join(@RequestBody MemberVO memberVO) {
		memberService.join(memberVO);
		return true;
	}
	
//	@PostMapping("/member/login")
//	public Object login(@RequestBody MemberVO memberVO) {
//		return gson.toJson(memberService.login(memberVO).get());
//	}
	
	@PostMapping("/member/login")
	public ResponseToken login(@RequestBody MemberVO memberVO) {
		MemberVO selectMemberVO = memberService.login(memberVO).get();
		AuthVO authVO = new AuthVO(selectMemberVO);
		return new ResponseToken(jwtService.createToken(authVO));
	}

	@PostMapping("/findId")
	public Object findId(@RequestBody MemberVO memberVO) {
		String mvo = memberService.findId(memberVO);
		return gson.toJson(mvo);
	}

	@PostMapping("/confirmPassword")
	public void confirmPassword(@RequestBody MemberVO memberVO) {
		 memberService.confirmPassword(memberVO);
	}
	@PostMapping("/changePassword")
	public void changePassword(@RequestBody MemberVO memberVO) {
		memberService.changePassword(memberVO);
	}

	@PostMapping("/myPage")
	public Object myPage(@RequestBody MemberVO memberVO) {
		MemberVO mvo = memberService.myPage(memberVO).get();
		return gson.toJson(mvo);
	}

	@PutMapping("/changeMember")
	public void update(@RequestBody MemberVO memberVO) {
		memberService.update(memberVO);
	}

	@DeleteMapping("/quit")
	public void quit(@RequestBody MemberVO memberVO) {
		memberService.delete(memberVO);
	}

}
