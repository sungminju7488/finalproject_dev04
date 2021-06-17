package com.dev4.sunbbang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Integer>{

	Optional<MemberVO> findByMemberIdAndPassword(String memberId, String password);
	
	Optional<MemberVO> findByPhoneNumberAndEmail(String phoneNumber, String email);
	
	Optional<MemberVO> findByMemberId(String memberId);
	
	Optional<MemberVO> deleteByMemberIdAndPassword(String memberId, String password);
	
}
