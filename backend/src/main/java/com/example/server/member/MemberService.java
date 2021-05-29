package com.example.server.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {
	@Autowired
	MemberRepository mr;
	
	public void join(MemberVO memberVO) {
		mr.save(memberVO);
	}
	public Optional<MemberVO> myPage(MemberVO memberVO){
		return mr.findById(memberVO.getMemberId());
	}
	
	public void update(MemberVO memberVO) {
		mr.save(memberVO);
	}
//	public Optional<MemberVO> delete(MemberVO memberVO){
//		return mr.deleteByIdAndPassword(memberVO.getMemberId(), memberVO.getPassword());
//	}
}
