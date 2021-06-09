package com.dev4.sunbbang.article.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev4.sunbbang.article.ArticleService;
import com.dev4.sunbbang.model.ArticleVO;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDAO articleDAO;
	
	@Override
	public List<ArticleVO> getArticleList(ArticleVO vo) {
		return articleDAO.getArticleList(vo);
	}

}
