package com.dev4.sunbbang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
@Data
@SequenceGenerator(name="memberSeq",sequenceName="member_seq",initialValue = 1 , allocationSize = 1)
@Entity
@Table(name="MEMBER")
public class MemberVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberSeq")
	@Column(name="MEMBERSEQ",nullable=false, unique = true)
	private int memberSeq; 
	@NotBlank(message = "아이디를 입력해주세요.")
	@Column(name="MEMBERID",length=16)
	@Pattern(regexp = "/^[a-zA-Z0-9]((?=.*\\d)|(?=.*\\W)).{5,16}$/")//아이디는5 ~16자의 영문 대소문자와 특수문자,숫자로만 입력
	private String memberId;
	@NotBlank(message = "패스워드를 입력해주세요.")
	@Column(name="PASSWORD",length=16)
	@Pattern(regexp = "/^[a-zA-Z0-9]((?=.*\\\\d)|(?=.*\\\\W)).{8,16}$/")//비밀번호는 8~16자의 영문 대소문자와 특수문자,숫자로만 입력
	private String password;
	@NotBlank(message = "이름을 입력해주세요.")
	@Column(name="NAME",length=50,nullable=false)
	private String name;
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Column(name="NICKNAME",length=16)
	@Pattern(regexp ="/^[a-zA-Z0-9] {4,16}")
	private String nickName;
	@Column(name="ADDRESS1",length=100)
	private String address1;
	@Column(name="ADDRESS2",length=100)
	private String address2;
	@NotBlank(message = "핸드폰번호를 입력해주세요.")
	@Column(name="PHONENUMBER",length=11)
	@Pattern(regexp ="/(\\d{3}).*(\\d{3}).*(\\d{4})/")
	private String phoneNumber;
	@NotBlank(message = "이메일을 입력해주세요.")
	@Column(name="EMAIL",length=40)
	@Email
	private String email;
	@NotBlank(message = "생년월일을 입력해주세요.")
	@Column(name="BIRTHDAY",length=10)
	@Pattern(regexp ="/^\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}$/")
	private String birthDay;
	@Column(name="SEX",length=6)
	private String sex;
	@Column(name="JOINDATE",length=10)
	private String joinDate;
	@Pattern(regexp ="/^\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}$/")
	@Column(name="GRADE",length=1,nullable=false)
	private String grade;
	@Column(name="ALARMSET",length=255)
	private String alarmSet;
	@Column(name="FOLLOWSET",length=255)
	private String followSet;
	@Column(name="FLATFORM",length=50,nullable=false)
	private String flatForm;
	
	
	
	
}
