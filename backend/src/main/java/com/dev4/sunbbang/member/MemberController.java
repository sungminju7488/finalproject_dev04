package com.dev4.sunbbang.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/member/join")
	public boolean join(@RequestBody MemberVO memberVO) {
		memberService.join(memberVO);
		return true;
	}
	
	@RequestMapping("/member/login")
	public ResponseToken login(@RequestBody MemberVO memberVO) {
		return new ResponseToken(jwtService.createToken(memberService.login(memberVO)));
	}

	@RequestMapping("/findId")
	public Object findId(@RequestBody MemberVO memberVO) {
		String mvo = memberService.findId(memberVO);
		return gson.toJson(mvo);
	}

	@RequestMapping("/confirmPassword")
	public void confirmPassword(@RequestBody MemberVO memberVO) {
		 memberService.changePassword(memberVO);
	}
	@RequestMapping("/changePassword")
	public void changePassword(@RequestBody MemberVO memberVO) {
		memberService.changePassword(memberVO);
	}

	@RequestMapping("/myPage")
	public Object myPage(@RequestBody MemberVO memberVO) {
		MemberVO mvo = memberService.myPage(memberVO).get();
		return gson.toJson(mvo);
	}

	@RequestMapping("/changeMember")
	public Boolean update(@RequestBody MemberVO memberVO) {
		try {
		memberService.changeMember(memberVO);
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("/quit")
	public void quit(@RequestBody MemberVO memberVO) {
		memberService.quit(memberVO);
	}

}
