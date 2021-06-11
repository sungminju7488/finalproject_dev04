package com.dev4.sunbbang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.PageVO;

public interface ArticleRepository extends JpaRepository<ArticleVO, String> {

	Optional<ArticleVO> findByArticleSeq(int no);
//	Page<ArticleVO> findAll(PageVO pageVO);
//	public List<ArticleVO> findAll(ArticleVO vo,Pageable pageVO);

}
