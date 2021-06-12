package com.dev4.sunbbang.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.repository.BakeryRepository;
import com.dev4.sunbbang.repository.MemberRepository;

@Transactional
@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	BakeryRepository bakeryRepository;
	
	public void join(MemberVO memberVO) {
		memberRepository.save(memberVO);
	}
	
	public AuthVO login(MemberVO memberVO) {
		MemberVO loginMember = memberRepository.findByMemberIdAndPassword(memberVO.getMemberId(), memberVO.getPassword()).get();
		AuthVO authVO = new AuthVO(loginMember);
		if(authVO.getGrade().equals("1")) {
			authVO.setCopRegNum(bakeryRepository.findByMemberVO(loginMember).get().getCopRegNum());
		}
		return authVO;
	}
	
	public String findId(MemberVO memberVO){
		return memberRepository.findByPhoneNumberAndEmail(memberVO.getPhoneNumber(), memberVO.getEmail()).get().getMemberId();
	}
	
	public boolean findPassword(MemberVO memberVO){
		if(memberRepository.findByPhoneNumberAndEmail(memberVO.getPhoneNumber(), memberVO.getEmail()).get()!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void changePassword(MemberVO memberVO) {
		memberRepository.save(memberVO);
	}
	
	public Optional<MemberVO> myPage(MemberVO memberVO){
		return memberRepository.findByMemberId(memberVO.getMemberId());
	}
	
	public void changeMember(MemberVO memberVO) {
		memberRepository.save(memberVO);
	}
	
	public void quit(MemberVO memberVO){
		memberRepository.deleteByMemberIdAndPassword(memberVO.getMemberId(), memberVO.getPassword());
	}
}
