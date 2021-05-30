package com.example.server.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.model.MemberVO;

@Transactional
@Service
public class BakeryService {
	@Autowired
	MemberRepository mr;
	
	public void join(MemberVO memberVO) {
		mr.save(memberVO);
	}
	public String findId(MemberVO memberVO){
		return mr.findByPhoneNumberAndEMail(memberVO.getPhoneNumber(), memberVO.getEMail()).get().getMemberId();
	}
	public String findPassword(MemberVO memberVO){
		return mr.findByPhoneNumberAndEMail(memberVO.getPhoneNumber(), memberVO.getEMail()).get().getPassword();
	}
	public Optional<MemberVO> myPage(MemberVO memberVO){
		return mr.findByMemberId(memberVO.getMemberId());
	}
	public void update(MemberVO memberVO) {
		mr.save(memberVO);
	}
	public Optional<MemberVO> delete(MemberVO memberVO){
		return mr.deleteByMemberIdAndPassword(memberVO.getMemberId(), memberVO.getPassword());
	}
}
