package com.dev4.sunbbang.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthVO {

	private int memberSeq;
	private String memberId;
	private String name;
	private String nickName;
	private String grade;
	private String alarmSet;
	private String followSet;
	
	public AuthVO() {}
	
	public AuthVO(MemberVO memberVO) {
		this.memberSeq = memberVO.getMemberSeq();
		this.memberId = memberVO.getMemberId();
		this.name = memberVO.getName();
		this.nickName = memberVO.getNickName();
		this.grade = memberVO.getGrade();
		this.alarmSet = memberVO.getAlarmSet();
		this.followSet = memberVO.getFollowSet();
	}
}
