package com.dev4.sunbbang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Entity
@Table(name="BAKERY")
public class BakeryVO {
	
	public BakeryVO() {
	}
	
	public BakeryVO(BakeryVO vo) {
		this.copRegNum = vo.getCopRegNum();
		this.manager = vo.getManager();
		this.storeName = vo.getStoreName();
		this.storeAddress1 = vo.getStoreAddress1();
		this.storeAddress2 = vo.getStoreAddress2();
		this.storeContact = vo.getStoreContact();
		this.bakeryPath = vo.getBakeryPath();
		this.businessHour = vo.getBusinessHour();
		this.holiday = vo.getHoliday();
		this.specialHoliday = vo.getSpecialHoliday();
		this.eatable = vo.getEatable();
		this.latitude = vo.getLatitude();
		this.longitude = vo.getLongitude();
		this.boardSet = vo.getBoardSet();
		this.memberVO = vo.getMemberVO();
	}

	@Id
	@Column(name="COPREGNUM",nullable=false,length=10)
	private String copRegNum;
	@Column(name="MANAGER",nullable=false,length=50)
	private String manager;
	@Column(name="STORENAME",nullable=false,length=50)
	private String storeName;
	@Column(name="STOREADDRESS1",nullable=false,length=100)
	private String storeAddress1;
	@Column(name="STOREADDRESS2",length=100)
	private String storeAddress2;
	@Column(name="STORECONTACT",nullable=false,length=11)
	private String storeContact;
	@Column(name="BAKERYPATH",nullable=false,length=255)
	private String bakeryPath;
	@Column(name="BUSINESSHOURL",nullable=false,length=20)
	private String businessHour;
	@Column(name="HOLIDAY",length=100)
	private String holiday;
	@Column(name="SPECIALHOLIDAY",length=100)
	private String specialHoliday;
	@Column(name="EATABLE",nullable=false,length=1)
	private char eatable;
	@Column(name="LATITUDE",nullable=false,length=10)
	private String latitude;
	@Column(name="LONGITUDE",nullable=false,length=10)
	private String longitude;
	@Column(name="BOARDSET",length=1)
	private char boardSet;
	@OneToOne
	@JoinColumn(name="MEMBERSEQ", nullable = false)
	private MemberVO memberVO;
}
