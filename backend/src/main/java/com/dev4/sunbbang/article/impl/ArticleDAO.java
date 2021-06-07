package com.dev4.sunbbang.article.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev4.sunbbang.model.ArticleVO;

@Repository
public class ArticleDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<ArticleVO> getArticleList(ArticleVO vo){
		return mybatis.selectList("ArticleDAO.getArticleList", vo);
	}
}
