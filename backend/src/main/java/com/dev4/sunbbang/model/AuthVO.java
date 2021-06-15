package com.dev4.sunbbang.model;

import lombok.Data;

@Data
public class AuthVO {

	private int memberSeq;
	private String memberId;
	private String name;
	private String nickName;
	private String grade;
	private String alarmSet;
	private String followSet;
	private String CopRegNum;
	
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
