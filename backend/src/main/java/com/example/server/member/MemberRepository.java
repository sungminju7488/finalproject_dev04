package com.example.server.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.model.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Integer>{

	Optional<MemberVO> deleteByMemberIdAndPassword(String memberId, String password);
	Optional<MemberVO> findByMemberIdAndPassword(String memberId, String password);
	Optional<MemberVO> findByPhoneNumberAndEMail(String phoneNumber, String eMail);
	Optional<MemberVO> findByMemberId(String memberId);

}
