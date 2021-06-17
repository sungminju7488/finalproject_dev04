package com.dev4.sunbbang.article;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.dev4.sunbbang.util.Define;

@Transactional
@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	BakeryRepository bakeryRepository;

	public Page<ArticleVO> articleList(BakeryVO bakeryVO, PageVO pageVO) {
		return articleRepository.findByBakeryVOOrderByArticleSeqDesc(bakeryVO, PageRequest.of(pageVO.getPageNo(), pageVO.getPageSize()))
				.get();
	}

	public void writeArticle(MemberVO memberVO, ArticleVO articleVO, BakeryVO bakeryVO, MultipartFile image,
			String imageName) throws IOException {
		bakeryVO = bakeryRepository.findById(bakeryVO.getCopRegNum()).get();
		articleVO.setBakeryVO(bakeryVO);
		articleVO.setWriterSeq(memberVO.getMemberSeq());
		articleVO.setWriterNickname(memberVO.getNickName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		articleVO.setRegDate(format.format(new Date()));
		articleRepository.save(articleVO);
		if (!image.isEmpty()) {
			int articleSeq = articleRepository.getArticleSeq().get();
			String fileName = articleSeq + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
			String path = Define.IMAGE_LOAD_PATH + "article/" + fileName;
			String savePath = Define.IMAGE_SAVE_PATH + "article/" + fileName;
			image.transferTo(new File(savePath));
			articleVO.setArticlePath(path);
			articleVO.setArticleSavePath(savePath);
		}
		articleRepository.save(articleVO);
	}

	public ArticleVO readArticle(ArticleVO articleVO) {
		return articleRepository.findByArticleSeq(articleVO.getArticleSeq()).get();
	}

	public void modifyArticle(ArticleVO articleVO, BakeryVO bakeryVO, MultipartFile image, String imageName)
			throws IOException {
		if (image != null && !image.isEmpty()) {
			String fileName = articleVO.getArticleSeq() + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
			String path = Define.IMAGE_LOAD_PATH + "article/" + fileName;
			String savePath = Define.IMAGE_SAVE_PATH + "article/" + fileName;
			image.transferTo(new File(savePath));
			articleVO.setArticlePath(path);
			articleVO.setArticleSavePath(savePath);
		}
		articleVO.setBakeryVO(bakeryRepository.findById(bakeryVO.getCopRegNum()).get());
		articleRepository.save(articleVO);
	}

	public void deleteArticle(ArticleVO articleVO) {
		articleRepository.delete(articleVO);
		if (articleVO.getArticleSavePath() != null) {
			File file = new File(articleVO.getArticleSavePath());
			file.delete();
		}
	}

	public void writeReply(ArticleVO articleVO) {
		articleRepository.save(articleVO);
	}

	public void deleteReply(ArticleVO articleVO) {
		articleVO.setReply(null);
		articleRepository.save(articleVO);
	}

//	public void reportArticle(ArticleVO vo) {
//		int totalReport = Integer.parseInt(vo.getReport());
//		if (totalReport < 5)
//			totalReport = totalReport + 1;
//		else
//			articleRepository.reportArticle(vo);
//	}
}