//package com.dev4.sunbbang.article;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.dev4.sunbbang.model.ArticleVO;
//
//
//@Controller
//@SessionAttributes("article")
//public class ArticleController {
//	@Autowired
//	private ArticleService articleService;
//	
//	@RequestMapping("/list.do")
//	public String getBoardList(ArticleVO vo, Model model) {
//		model.addAttribute("boardList", articleService.getArticleList(vo));
//		return "getBoardList.jsp"; 
//	}
//	
//}
