package com.dev4.sunbbang.article;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.ArticleVO;
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
	public Object articleList(@RequestBody PageVO pageVO){
		Page<ArticleVO> mvo = articleService.articleList((Pageable) pageVO);
		return gson.toJson(mvo);
	}
	@PostMapping("/article/wrtieArticle")
	public void wrtieArticle(@RequestBody ArticleVO vo){
		 articleService.wrtieArticle(vo);
	}
	@GetMapping("/article/readArticle")
	public Object readArticle(@RequestBody ArticleVO vo) {
		Optional<ArticleVO> mvo= articleService.readArticle(vo);
		return gson.toJson(mvo);
	}
	@PostMapping("/article/modifyArticle")
	public void modifyArticle(@RequestBody ArticleVO vo) {
		 articleService.modifyArticle(vo);
	}
	@PostMapping("/article/deleteArticle")
	public void deleteArticle(@RequestBody ArticleVO vo) {
		 articleService.deleteArticle(vo);
	}
	@PostMapping("/article/writeReply")
	public void writeReply(@RequestBody ArticleVO vo) {
		 articleService.writeReply(vo);
	}
	@PostMapping("/article/modifyReply")
	public void modifyReply(@RequestBody ArticleVO vo) {
		 articleService.modifyReply(vo);
	}
	@PostMapping("/article/deleteReply")
	public void deleteReply(@RequestBody ArticleVO vo) {
		 articleService.deleteReply(vo);
	}
	@PostMapping("/article/reportArticle")
	public void reportArticle(@RequestBody ArticleVO vo) {
		 articleService.reportArticle(vo);
	}
}
