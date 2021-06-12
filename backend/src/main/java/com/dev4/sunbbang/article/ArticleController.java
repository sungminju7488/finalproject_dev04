package com.dev4.sunbbang.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.PageVO;
import com.google.gson.Gson;
@Async
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	Gson gson;
	
	@PostMapping("/article/articleList")
	public Object articleList(@RequestBody BakeryVO bakeryVO,PageVO pageVO){
		pageVO.setPageSize(10);
		Page<ArticleVO> mvo = articleService.articleList(bakeryVO,(Pageable) pageVO);
		return gson.toJson(mvo);
	}
	@PostMapping("/article/writeArticle")
	public Boolean writeArticle(@RequestBody ArticleVO vo){
		try {
		 articleService.writeArticle(vo);
		 return true;
		}catch (Exception e) {
			return false;
		}
	}
	@PostMapping("/article/readArticle")
	public Object readArticle(@RequestBody ArticleVO vo) {
		ArticleVO mvo= articleService.readArticle(vo);
		return gson.toJson(mvo);
	}
	@PostMapping("/article/modifyArticle")
	public Boolean modifyArticle(@RequestBody ArticleVO vo) {
		try {
			articleService.modifyArticle(vo);
			 return true;
			}catch (Exception e) {
				return false;
			}
		 
	}
	@PostMapping("/article/deleteArticle")
	public Boolean deleteArticle(@RequestBody ArticleVO vo) {
		try {
			articleService.deleteArticle(vo);
			 return true;
			}catch (Exception e) {
				return false;
			}
	}
	@PostMapping("/article/writeReply")
	public Boolean writeReply(@RequestBody ArticleVO vo) {
		try {
			 articleService.writeReply(vo);
			 return true;
			}catch (Exception e) {
				return false;
			}
	}
	@PostMapping("/article/modifyReply")
	public Boolean modifyReply(@RequestBody ArticleVO vo) {
		try {
			 articleService.modifyReply(vo);
			 return true;
			}catch (Exception e) {
				return false;
			}
	}
	@PostMapping("/article/deleteReply")
	public Boolean deleteReply(@RequestBody ArticleVO vo) {
		try {
			 articleService.deleteReply(vo);
			 return true;
			}catch (Exception e) {
				return false;
			}
	}
	@PostMapping("/article/reportArticle")
	public Boolean reportArticle(@RequestBody ArticleVO vo) {
		try {
			articleService.reportArticle(vo);
			 return true;
			}catch (Exception e) {
				return false;
			}
	}
}
