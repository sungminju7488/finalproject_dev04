package com.dev4.sunbbang.member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.repository.BakeryRepository;
import com.dev4.sunbbang.repository.MemberRepository;
import com.dev4.sunbbang.util.Define;

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
	
	public List<String> findId(MemberVO memberVO){
		List<MemberVO> list = memberRepository.findByPhoneNumberAndEmail(memberVO.getPhoneNumber(), memberVO.getEmail()).get();
		List<String> returnList = new ArrayList<String>();
		for(MemberVO vo : list) {
			String id = vo.getMemberId();
			if(id.length()<=7) {
				id = id.substring(id.length()-3, id.length());
				id += "***";
			} else {
				id = id.substring(id.length()-4, id.length());
				id += "****";
			}
			returnList.add(id);
		}
		return returnList;
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
		if(memberVO.getGrade().equals("1")) {
			File file = new File(Define.IMAGE_SAVE_PATH
					+ bakeryRepository.findByMemberVO(memberVO).get().getBakeryPath());
			bakeryRepository.deleteByMemberVO(memberVO);
			file.delete();
		}
		memberRepository.deleteByMemberIdAndPassword(memberVO.getMemberId(), memberVO.getPassword());
	}
}
