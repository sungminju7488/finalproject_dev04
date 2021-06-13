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
@SequenceGenerator(name="articleSeq",sequenceName="article_seq",initialValue = 1 , allocationSize = 1)
@Entity
@Table(name="ARTICLE")
public class ArticleVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleSeq")
	@Column(name="ARTICLESEQ",nullable=false,length=8)
	private int articleSeq;
	@Column(name="TITLE",nullable=false,length=100)
	private String title;
	@Column(name="WRITERSEQ",nullable=false,length=10)
	private int writerSeq;
	@Column(name="WRITERNICKNAME",nullable=false,length=16)
	private String writerNickname;
	@Column(name="REGDATE",nullable=false,length=10)
	private String regDate;
	@Column(name="SCORE",length=1)
	private int score;
	@Column(name="REPORT",nullable=false,length=255)
	private String report;
	@Column(name="CONTENT",nullable=false,length=255)
	private String content;
	@Column(name="ARTICLEPATH",length=255)
	private String articlePath;
	@Column(name="REPLY",length=255)
	private String reply;
	@ManyToOne
	@JoinColumn(name="COPREGNUM", nullable=false)
	private BakeryVO bakeryVO;
}
