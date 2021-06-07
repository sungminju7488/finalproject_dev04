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
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Validated
@Data
@Getter@Setter
@SequenceGenerator(name = "articleSeq",sequenceName = "no_seq",initialValue = 1 , allocationSize = 1)
@Entity
@Table(name ="ARTICLE")
public class ArticleVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="no_seq")
	@Column(name="ARTICLESEQ",nullable=false, unique = true)
	private int articleSeq;
	
	@Column(name="TITLE",nullable=false)
	private String title;
	
	@Column(name="WRITERSEQ",nullable=false)
	private int writerSeq;
	
	@Column(name="WRITERNICKNAME",nullable=false)
	private String writerNickname;
	
	@Column(name="REGDATE",nullable=false)
	private String regDate;
	
	@Column(name="SCORE")
	private int score;
	
	@Column(name="REPORT",nullable=false)
	private String report;
	
	@ManyToOne
	@JoinColumn(name ="MEMBERSEQ")
	@Column(name="BAKERYSEQ",nullable=false)
	private int bakerySeq;
	
}
