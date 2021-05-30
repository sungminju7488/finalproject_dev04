package com.example.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SequenceGenerator(name="memberSeq",sequenceName="no_seq",initialValue = 1 , allocationSize = 1)
@Entity
@Table(name="MEMBER")
public class MemberVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "no_seq")
	@Column(name="MEMBERSEQ",nullable=false, unique = true)
	private int memberSeq; 
	@Column(name="MEMBERID",length=16)
	private String memberId;
	@Column(name="PASSWORD",length=16)
	private String password;
	@Column(name="NAME",length=50,nullable=false)
	private String name;
	@Column(name="NICKNAME",length=16)
	private String nickName;
	@Column(name="ADDRESS1",length=100)
	private String address1;
	@Column(name="ADDRESS2",length=100)
	private String address2;
	@Column(name="PHONENUMBER",length=11)
	private String phoneNumber;
	@Column(name="EMAIL",length=40)
	private String eMail;
	@Column(name="BIRTHDAY",length=10)
	private String birthDay;
	@Column(name="SEX",length=6)
	private String sex;
	@Column(name="JOINDATE",length=10)
	private String joinDate;
	@Column(name="GRADE",length=1,nullable=false)
	private String grade;
	@Column(name="ALARMSET",length=255)
	private String alarmSet;
	@Column(name="FOLLOWSET",length=255)
	private String followSet;
	@Column(name="FLATFORM",length=50,nullable=false)
	private String flatForm;
	
	
	
	
}
