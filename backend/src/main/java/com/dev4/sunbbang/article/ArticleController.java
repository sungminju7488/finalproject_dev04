package com.dev4.sunbbang.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.model.PageVO;
import com.google.gson.Gson;

@Async
@RestController
public class ArticleController {
	@Autowired
	ArticleService articleService;

	@Autowired
	Gson gson;

	@PostMapping("/article/articleList")
	public Object articleList(@RequestBody BakeryVO bakeryVO, PageVO pageVO) {
		pageVO.setPageSize(10);
		Page<ArticleVO> mvo = articleService.articleList(bakeryVO, pageVO);
		return gson.toJson(mvo);
	}

	@PostMapping("/article/writeArticle")
	public Boolean writeArticle(MemberVO memberVO, ArticleVO articleVO, BakeryVO bakeryVO, MultipartFile image,
			String imageName) {
		try {
			articleService.writeArticle(memberVO, articleVO, bakeryVO, image, imageName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/article/readArticle")
	public Object readArticle(@RequestBody ArticleVO articleVO) {
		ArticleVO mvo = articleService.readArticle(articleVO);
		return gson.toJson(mvo);
	}

	@PostMapping("/article/modifyArticle")
	public Boolean modifyArticle(ArticleVO articelVO, BakeryVO bakeryVO, MultipartFile image, String imageName) {
		try {
			articleService.modifyArticle(articelVO, bakeryVO, image, imageName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@PostMapping("/article/deleteArticle")
	public Boolean deleteArticle(@RequestBody ArticleVO articleVO) {
		try {
			articleService.deleteArticle(articleVO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/article/writeReply")
	public Boolean writeReply(@RequestBody ArticleVO vo) {
		try {
			articleService.writeReply(vo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/article/deleteReply")
	public Boolean deleteReply(@RequestBody ArticleVO vo) {
		try {
			articleService.deleteReply(vo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	@PostMapping("/article/reportArticle")
//	public Boolean reportArticle(@RequestBody ArticleVO vo) {
//		try {
//			articleService.reportArticle(vo);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
}
