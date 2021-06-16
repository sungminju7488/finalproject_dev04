package com.dev4.sunbbang.bakery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.model.PageVO;
import com.dev4.sunbbang.repository.BakeryRepository;
import com.dev4.sunbbang.repository.FoodRepository;
import com.dev4.sunbbang.repository.MemberRepository;
import com.dev4.sunbbang.util.Define;

@Transactional
@Service
public class BakeryService {

	@Autowired
	BakeryRepository bakeryRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	FoodRepository foodRepository;

	public AuthVO joinBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName)
			throws IOException {
		memberVO = memberRepository.getById(memberVO.getMemberSeq());
		memberVO.setGrade("1");
		bakeryVO.setMemberVO(memberVO);
		if (!image.isEmpty()) {
			String fileName = bakeryVO.getCopRegNum() + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
			String path = Define.IMAGE_LOAD_PATH + "bakery/" + fileName;
			String savePath = Define.IMAGE_SAVE_PATH + "bakery/" + fileName;
			image.transferTo(new File(savePath));
			bakeryVO.setBakeryPath(path);
			bakeryVO.setBakerySavePath(savePath);
		}
		memberRepository.save(memberVO);
		bakeryRepository.save(bakeryVO);
		AuthVO authVO = new AuthVO(memberVO);
		authVO.setCopRegNum(bakeryVO.getCopRegNum());
		return authVO;
	}

	public BakeryVO myShop(MemberVO memberVO) {
		BakeryVO bakeryVO = bakeryRepository.findByMemberVO(memberVO).get();
		bakeryVO.setBakeryPath(bakeryVO.getBakeryPath());
		return bakeryVO;
	}

	public void changeBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName)
			throws IOException {
		memberVO = memberRepository.getById(memberVO.getMemberSeq());
		bakeryVO.setMemberVO(memberVO);
		if (image != null && !image.isEmpty()) {
			String fileName = bakeryVO.getCopRegNum() + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
			String path = Define.IMAGE_LOAD_PATH + "bakery/" + fileName;
			String savePath = Define.IMAGE_SAVE_PATH + "bakery/" + fileName;
			image.transferTo(new File(savePath));
			bakeryVO.setBakeryPath(path);
			bakeryVO.setBakerySavePath(savePath);
		}
		bakeryRepository.save(bakeryVO);
	}

	public List<FoodVO> menuList(BakeryVO bakeryVO) {
		List<FoodVO> list = foodRepository.findByBakeryVO(bakeryRepository.findById(bakeryVO.getCopRegNum()).get())
				.get();
		return list;
	}

	public void addMenu(BakeryVO bakeryVO, FoodVO foodVO, MultipartFile image, String imageName) throws IOException {
		bakeryVO = bakeryRepository.findById(bakeryVO.getCopRegNum()).get();
		foodVO.setBakeryVO(bakeryVO);
		foodRepository.save(foodVO);
		if (!image.isEmpty()) {
			int foodSeq = foodRepository.getFoodSeq().get();
			String fileName = foodSeq + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
			String path = Define.IMAGE_LOAD_PATH + "food/" + fileName;
			String savePath = Define.IMAGE_SAVE_PATH + "food/" + fileName;
			image.transferTo(new File(savePath));
			foodVO.setFoodPath(path);
			foodVO.setFoodSavePath(savePath);
		}
		foodRepository.save(foodVO);
	}

	public void modifyMenu(BakeryVO bakeryVO, FoodVO foodVO, MultipartFile image, String imageName) throws IOException {
		if (image != null && !image.isEmpty()) {
			String fileName = foodVO.getFoodSeq() + "." + imageName.substring(imageName.lastIndexOf(".") + 1);
			String path = Define.IMAGE_LOAD_PATH + "food/" + fileName;
			String savePath = Define.IMAGE_SAVE_PATH + "food/" + fileName;
			image.transferTo(new File(savePath));
			foodVO.setFoodPath(path);
			foodVO.setFoodSavePath(savePath);
		}
		foodVO.setBakeryVO(bakeryRepository.findById(bakeryVO.getCopRegNum()).get());
		foodRepository.save(foodVO);
	}

	public void deleteMenu(FoodVO foodVO) {
		foodRepository.delete(foodVO);
		File file = new File(foodVO.getFoodSavePath());
		file.delete();
	}

	public char boardToggle(BakeryVO bakeryVO) {
		switch (bakeryRepository.findById(bakeryVO.getCopRegNum()).get().getBoardSet()) {
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
		Page<BakeryVO> page = bakeryRepository.findByStoreNameContaining(pageVO.getKeyword(),
				PageRequest.of(pageVO.getPageNo(), pageVO.getPageSize())).get();
		return page;
	}

//	public void setFollow(AuthVO authVO, BakeryVO bakeryVO) {
//		String followSet = authVO.getFollowSet() + bakeryVO.getCopRegNum() + ",";
//		memberRepository.modifyToFollowSet(followSet, authVO.getMemberSeq());
//	}

	public List<FoodVO> menuViewList(BakeryVO bakeryVO) {
		List<FoodVO> list = foodRepository.findByBakeryVO(bakeryVO).get();
		return list;
	}

	public void setAlarm(AuthVO authVO, FoodVO foodVO) {
		MemberVO memberVO = memberRepository.findById(authVO.getMemberSeq()).get();
		memberVO.setAlarmSet(memberVO.getAlarmSet() + foodVO.getFoodSeq() + ",");
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
			FoodVO vo = foodRepository.findById(foodVO.getFoodSeq()).get();
			returnList.add(vo);
		}
		return returnList;
	}

	public MemberVO deleteAlarmApp(MemberVO memberVO) {
		MemberVO mvo = memberRepository.findById(memberVO.getMemberSeq()).get();
		mvo.setAlarmSet(memberVO.getAlarmSet());
		return mvo;
	}

//	public MemberVO deleteAlarm(AuthVO authVO, FoodVO foodVO) {
//		StringTokenizer st = new StringTokenizer(authVO.getAlarmSet());
//		String alarmSet = "";
//		while (st.hasMoreTokens()) {
//			int token = Integer.parseInt(st.nextToken(","));
//			if (foodVO.getFoodSeq() != token)
//				alarmSet += token + ",";
//		}
//		memberRepository.modifyToAlarmSet(alarmSet, authVO.getMemberSeq());
//		return memberRepository.findById(authVO.getMemberSeq()).get();
//	}

	public Page<FoodVO> searchFood(PageVO pageVO) {
		Page<FoodVO> page = foodRepository
				.findByFoodNameContaining(pageVO.getKeyword(), PageRequest.of(pageVO.getPageNo(), pageVO.getPageSize()))
				.get();
		return page;
	}
}
