package com.dev4.sunbbang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Data
@SequenceGenerator(name="foodSeq",sequenceName="food_seq",initialValue = 1 , allocationSize = 1)
@Entity
@Table(name="FOOD")
public class FoodVO {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
	@Column(name="FOODSEQ",nullable=false,length=8)
	private int foodSeq;
	@Column(name="FOODNAME",nullable=false,length=50)
	private String foodName;
	@Column(name="KIND",nullable=false,length=50)
	private String kind;
	@Column(name="FOODPATH",nullable=false,length=255)
	private String foodPath;
	@Column(name="PRICE",nullable=false,length=8)
	private int price;
	@Column(name="SALETIME",length=8)
	private String saleTime;
	@JoinColumn(name="BAKERYSEQ",nullable=false,length=8)
	private int bakerySeq;
	
	
}
