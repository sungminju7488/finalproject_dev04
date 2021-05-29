package com.example.server.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.model.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String>{

	//Optional<MemberVO> findByIdAndPassword(String memberId, String password);

	//Optional<MemberVO> deleteByIdAndPassword(String memberId,String password);
}
