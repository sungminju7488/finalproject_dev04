package com.dev4.sunbbang.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.BakeryVO;

public interface ArticleRepository extends JpaRepository<ArticleVO, String> {

	Optional<ArticleVO> findByArticleSeq(int no);

	@Query(value = "UPDATE ARTICLE SET CONTENT=?0",nativeQuery = true)
	public void reportArticle(ArticleVO vo);
	
	@Query(value = "SELECT * FROM ARTICLE",nativeQuery = true)
	public Page<ArticleVO> findList(BakeryVO bakeryVO,Pageable pageVO);
}
