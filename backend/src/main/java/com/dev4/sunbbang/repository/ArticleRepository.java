package com.dev4.sunbbang.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.BakeryVO;

public interface ArticleRepository extends JpaRepository<ArticleVO, String> {

	public Optional<Page<ArticleVO>> findByBakeryVOOrderByArticleSeqDesc(BakeryVO bakeryVO, Pageable pagealbe);

	@Query(value = "SELECT ARTICLE_SEQ.CURRVAL FROM DUAL", nativeQuery = true)
	public Optional<Integer> getArticleSeq();

	Optional<ArticleVO> findByArticleSeq(int articleSeq);

//	@Query(value = "UPDATE ARTICLE SET CONTENT=?0", nativeQuery = true)
//	public void reportArticle(ArticleVO vo);
}
