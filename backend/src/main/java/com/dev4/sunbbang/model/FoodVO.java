package com.dev4.sunbbang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name="FOOD")
public class FoodVO {
	@Column(name="FOODSEQ",nullable=false,length=8)
	private String foodSeq;
	@Column(name="FOODNAME",nullable=false,length=50)
	private String foodName;
	@Column(name="KIND",nullable=false,length=50)
	private String kind;
	@Column(name="FOODPATH",nullable=false,length=255)
	private String foodPath;
	@Column(name="PRICE",nullable=false,length=8)
	private String price;
	@Column(name="SALETIME",length=8)
	private String saleTime;
	@Column(name="BAKERYSEQ",nullable=false,length=8)
	private String bakerySeq;
	
	
}
