package com.dev4.sunbbang.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
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
		try {
			memberService.join(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping("/member/login")
	public ResponseToken login(@RequestBody MemberVO memberVO) {
		return new ResponseToken(jwtService.createToken(memberService.login(memberVO)));
	}
	
	@PostMapping("/member/loginApp")
	public Object loginApp(@RequestBody MemberVO memberVO) {
		AuthVO authVO = memberService.login(memberVO); 
		return gson.toJson(authVO);
	}

	@PostMapping("/findId")
	public Object findId(@RequestBody MemberVO memberVO) {
		List<String> mvo = memberService.findId(memberVO);
		return gson.toJson(mvo);
	}

	@PostMapping("/findPassword")
	public boolean findPassword(@RequestBody MemberVO memberVO) {
		return memberService.findPassword(memberVO);
	}
	@PostMapping("/changePassword")
	public boolean changePassword(@RequestBody MemberVO memberVO) {
		try {
			memberService.changePassword(memberVO);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/myPage")
	public Object myPage(@RequestBody MemberVO memberVO) {
		MemberVO mvo = memberService.myPage(memberVO).get();
		return gson.toJson(mvo);
	}

	@PostMapping("/changeMember")
	public boolean update(@RequestBody MemberVO memberVO) {
		try {
			memberService.changeMember(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/quit")
	public boolean quit(@RequestBody MemberVO memberVO) {
		try {
			memberService.quit(memberVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
