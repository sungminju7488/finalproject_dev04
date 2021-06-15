package com.dev4.sunbbang.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;

public interface ArticleRepository extends JpaRepository<ArticleVO, String> {
	
	public Optional<Page<ArticleVO>> findByBakeryVO(BakeryVO bakeryVO, Pageable pagealbe);

	Optional<ArticleVO> findByArticleSeq(int no);

	@Query(value = "UPDATE ARTICLE SET CONTENT=?0",nativeQuery = true)
	public void reportArticle(ArticleVO vo);
	
	@Query(value = "SELECT * FROM ARTICLE",nativeQuery = true)
	public Page<ArticleVO> findList(String keyword,BakeryVO bakeryVO,Pageable pageVO);
	
	@Query(value="SELECT ARTICLESEQ.CURRVAL FROM ARTICLE", nativeQuery = true)
	public Optional<Integer> getArticleSeq();
}
