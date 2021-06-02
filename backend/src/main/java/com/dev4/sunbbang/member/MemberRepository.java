package com.dev4.sunbbang.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Integer>{

	Optional<MemberVO> deleteByMemberIdAndPassword(String memberId, String password);
	Optional<AuthVO> findByMemberIdAndPassword(String memberId, String password);
	Optional<MemberVO> findByPhoneNumberAndEmail(String phoneNumber, String email);
	Optional<MemberVO> findByMemberId(String memberId);

}
