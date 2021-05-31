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
@Table(name="BAKERY")
public class BakeryVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "no_seq") 
	@Column(name="COPREGNUM",nullable=false,length=10)
	private String copregnum;
	@Column(name="MANAGER",nullable=false,length=50)
	private String manager;
	@Column(name="STORENAME",nullable=false,length=50)
	private String storename;
	@Column(name="STOREADDRESS1",nullable=false,length=100)
	private String storeaddress1;
	@Column(name="STOREADDRESS2",nullable=false,length=100)
	private String storeaddress2;
	@Column(name="STORECONTACT",nullable=false,length=11)
	private String storecontact;
	@Column(name="BAKERYPATH",nullable=false,length=255)
	private String phoneNumber;
	@Column(name="BUSINESSHOURL",nullable=false,length=20)
	private String businesshour;
	@Column(name="HOLIDAY",length=100)
	private String holiday;
	@Column(name="SPECIALHOLIDAY",length=100)
	private String specialholiday;
	@Column(name="EATABLE",nullable=false,length=1)
	private String eayable;
	@Column(name="LATITUDE",nullable=false,length=10)
	private String latitude;
	@Column(name="LONGITUDE",nullable=false,length=10)
	private String longitude;
	@Column(name="BOARDSET",nullable=false,length=1)
	private String boardset;
	@Column(name="MEMBERSEQ",nullable=false,length=8)
	private String memberseq;
	
	
	
	
}
