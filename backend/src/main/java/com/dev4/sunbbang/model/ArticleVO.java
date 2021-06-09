package com.dev4.sunbbang.model;

public class ArticleVO {

	private int articleSeq;
	private String title;
	private int writerSeq;
	private String writerNickname;
	private String regDate;
	private int score;
	private String report;
	private int bakerySeq;
	public int getArticleSeq() {
		return articleSeq;
	}
	public void setArticleSeq(int articleSeq) {
		this.articleSeq = articleSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWriterSeq() {
		return writerSeq;
	}
	public void setWriterSeq(int writerSeq) {
		this.writerSeq = writerSeq;
	}
	public String getWriterNickname() {
		return writerNickname;
	}
	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public int getBakerySeq() {
		return bakerySeq;
	}
	public void setBakerySeq(int bakerySeq) {
		this.bakerySeq = bakerySeq;
	}
}
