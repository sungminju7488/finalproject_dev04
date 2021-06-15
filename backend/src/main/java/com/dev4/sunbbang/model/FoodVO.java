package com.dev4.sunbbang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@SequenceGenerator(name="foodSeq",sequenceName="food_seq",initialValue = 1 , allocationSize = 1)
@Entity
@Table(name="FOOD")
public class FoodVO {
	
	public FoodVO() {
	}
	
	public FoodVO(int foodSeq) {
		this.foodSeq = foodSeq;
	}
	
	public FoodVO(FoodVO vo) {
		this.foodSeq = vo.foodSeq;
		this.foodName = vo.foodName;
		this.kind = vo.kind;
		this.foodPath = vo.foodPath;
		this.price = vo.price;
		this.saleTime = vo.saleTime;
		this.bakeryVO = vo.bakeryVO;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foodSeq")
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
	@ManyToOne
	@JoinColumn(name="COPREGNUM", nullable = false)
	private BakeryVO bakeryVO;
	
	
	
}
