package com.dev4.sunbbang.bakery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.model.PageVO;
import com.dev4.sunbbang.repository.BakeryRepository;
import com.dev4.sunbbang.repository.FoodRepository;
import com.dev4.sunbbang.repository.MemberRepository;

@Transactional
@Service
public class BakeryService {

	@Autowired
	BakeryRepository bakeryRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	FoodRepository foodRepository;

	public void joinBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image) throws IOException {
		memberVO = memberRepository.getById(memberVO.getMemberSeq());
		memberVO.setGrade("1");
		if (!image.isEmpty()) {
			String path = "http://localhost:8080/bakery/" + bakeryVO.getCopRegNum() + "."
					+ image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
			bakeryVO.setBakeryPath(path);
			image.transferTo(new File(path));
		}
		memberRepository.save(memberVO);
		bakeryRepository.save(bakeryVO);
	}

	public BakeryVO myShop(BakeryVO bakeryVO) {
		return bakeryRepository.findByMemberVO(bakeryVO.getMemberVO()).get();
	}

	public void changeBakery(BakeryVO bakeryVO, MultipartFile image) throws IOException {
		if (!image.isEmpty()) {
			String path = "http://localhost:8080/bakery/" + bakeryVO.getCopRegNum() + "."
					+ image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
			bakeryVO.setBakeryPath(path);
			image.transferTo(new File(path));
		}
		bakeryRepository.save(bakeryVO);
	}

	public List<FoodVO> menuList(BakeryVO bakeryVO) {
		return foodRepository.findByBakeryVO(bakeryVO).get();
	}

	public void addMenu(FoodVO foodVO) {
		foodRepository.save(foodVO);
	}

	public void modifyMenu(FoodVO foodVO) {
		foodRepository.save(foodVO);
	}

	public void deleteMenu(FoodVO foodVO) {
		foodRepository.delete(foodVO);
	}

	public char boardToggle(BakeryVO bakeryVO) {
		switch(bakeryRepository.findById(bakeryVO.getCopRegNum()).get().getBoardSet()) {
		case 'T':
			bakeryVO.setBoardSet('F');
			bakeryRepository.save(bakeryVO);
			return 'F';
			
		default:
			bakeryVO.setBoardSet('T');
			bakeryRepository.save(bakeryVO);
			return 'T';
		}
	}

	public Page<BakeryVO> searchBakery(PageVO pageVO) {
		return bakeryRepository.findByStoreNameContaining(pageVO.getKeyword(),
				PageRequest.of(pageVO.getPageNo(), pageVO.getPageSize())).get();
	}

	public void setFollow(AuthVO authVO, BakeryVO bakeryVO) {
		String followSet = authVO.getFollowSet() + bakeryVO.getCopRegNum() + ",";
		memberRepository.modifyToFollowSet(followSet, authVO.getMemberSeq());
	}

	public void setAlarm(AuthVO authVO, FoodVO foodVO) {
		String alarmSet = authVO.getAlarmSet() + foodVO.getFoodSeq() + ",";
		memberRepository.modifyToAlarmSet(alarmSet, authVO.getMemberSeq());
	}

	public List<FoodVO> useAlarm(AuthVO authVO) {
		StringTokenizer st = new StringTokenizer(authVO.getAlarmSet());
		List<FoodVO> list = new ArrayList<FoodVO>();
		List<FoodVO> returnList = new ArrayList<FoodVO>();
		while (st.hasMoreTokens()) {
			int token = Integer.parseInt(st.nextToken(","));
			list.add(new FoodVO(token));
		}
		for (FoodVO foodVO : list) {
			returnList.add(foodRepository.findById(foodVO.getFoodSeq()).get());
		}
		return returnList;
	}

	public MemberVO deleteAlarm(AuthVO authVO, FoodVO foodVO) {
		StringTokenizer st = new StringTokenizer(authVO.getAlarmSet());
		String alarmSet = "";
		while (st.hasMoreTokens()) {
			int token = Integer.parseInt(st.nextToken(","));
			if (foodVO.getFoodSeq() != token)
				alarmSet += token + ",";
		}
		memberRepository.modifyToAlarmSet(alarmSet, authVO.getMemberSeq());
		return memberRepository.findById(authVO.getMemberSeq()).get();
	}

	public List<FoodVO> searchFood(FoodVO foodVO) {
		return foodRepository.findByFoodName(foodVO.getFoodName()).get();
	}
}
