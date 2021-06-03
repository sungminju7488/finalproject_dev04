package com.dev4.sunbbang.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Integer>{

//	Optional<MemberVO> deleteByMemberIdAndPassword(String memberId, String password);
//	Optional<MemberVO> findByMemberIdAndPassword(String memberId, String password);
//	Optional<MemberVO> findByPhoneNumberAndEMail(String phoneNumber, String eMail);
//	Optional<MemberVO> findByMemberId(String memberId);
	
	@Query("UPDATE MEMBER SET FOLLOWSET=?1 WHERE MEMBERSEQ=?2")
	public void modifyToFollowSet(String followSet, int MemberSeq);
	
	@Query("UPDATE MEMBER SET ALARMSET=?1 WHERE MEMBERSEQ=?2")
	public void modifyToAlarmSet(String followSet, int MemberSeq);

}
