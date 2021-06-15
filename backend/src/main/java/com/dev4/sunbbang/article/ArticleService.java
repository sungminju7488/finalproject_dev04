package com.dev4.sunbbang.article;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.ArticleVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.model.PageVO;
import com.dev4.sunbbang.repository.ArticleRepository;
import com.dev4.sunbbang.repository.BakeryRepository;

@Transactional
@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	BakeryRepository bakeryRepository;

	public Page<ArticleVO> articleList(BakeryVO bakeryVO,PageVO pageVO) {
		return articleRepository.findList(pageVO.getKeyword(),bakeryVO, PageRequest.of(pageVO.getPageNo(), pageVO.getPageSize()));
	}
	
	public void writeArticle(MemberVO memberVO,ArticleVO articleVO,BakeryVO bakeryVO,MultipartFile image,String imageName) throws IOException {
		articleVO.setArticleSeq(memberVO.getMemberSeq());
		articleVO.setWriterNickname(memberVO.getNickName());
		bakeryVO = bakeryRepository.findById(bakeryVO.getCopRegNum()).get();
		articleVO.setBakeryVO(bakeryVO);
		
		int articleSeq;
		if(articleRepository.findAll().size()==0) {
			articleSeq = 1;	
		}else {
			articleSeq = articleRepository.getArticleSeq().get()+1;
		}
		String fileName = articleSeq + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
		image.transferTo(new File("C://images/article/" + fileName));
		String path = "http://localhost:8080/images/article/" + fileName;
		articleVO.setArticlePath(path);
		articleRepository.save(articleVO);
	}
	public ArticleVO readArticle(ArticleVO vo) {
		return articleRepository.findByArticleSeq(vo.getArticleSeq()).get();
	}
	public void modifyArticle(ArticleVO vo) {
		articleRepository.save(vo);
	}
	public void deleteArticle(ArticleVO vo) {
		articleRepository.delete(vo);
		if (vo.getArticlePath() != null) {
			File file = new File(vo.getArticlePath());
			file.delete();
		}
	}
	public void writeReply(ArticleVO vo) {
		articleRepository.save(vo);
	}
	public void modifyReply(ArticleVO vo) {
		articleRepository.save(vo);
	}
	public void deleteReply(ArticleVO vo) {
		articleRepository.delete(vo);
	}
	public void reportArticle(ArticleVO vo) {
		int totalReport=Integer.parseInt(vo.getReport());
	if(totalReport<5)
		totalReport=totalReport+1;
	else 
		articleRepository.reportArticle(vo);
	}
}