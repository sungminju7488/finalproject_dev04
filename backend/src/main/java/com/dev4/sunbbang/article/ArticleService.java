package com.dev4.sunbbang.article;

import java.util.List;

import com.dev4.sunbbang.model.ArticleVO;

public interface ArticleService {
	
	public List<ArticleVO> getArticleList(ArticleVO vo);
}
