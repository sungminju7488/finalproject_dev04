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

	public AuthVO joinBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName) throws IOException {
		memberVO = memberRepository.getById(memberVO.getMemberSeq());
		memberVO.setGrade("1");
		bakeryVO.setMemberVO(memberVO);
		if (!image.isEmpty()) {
			String fileName = bakeryVO.getCopRegNum() + "." + imageName.substring(imageName.lastIndexOf(".")+1);
			image.transferTo(new File("C://images/bakery/" + fileName));
			String path = "http://localhost:8080/images/bakery/" + fileName;
			bakeryVO.setBakeryPath(path);
		}
		memberRepository.save(memberVO);
		bakeryRepository.save(bakeryVO);
		AuthVO authVO = new AuthVO(memberVO);
		authVO.setCopRegNum(bakeryVO.getCopRegNum());
		return authVO;
	}

	public BakeryVO myShop(MemberVO memberVO) {
		return bakeryRepository.findByMemberVO(memberVO).get();
	}

	public void changeBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName) throws IOException {
		memberVO = memberRepository.getById(memberVO.getMemberSeq());
		bakeryVO.setMemberVO(memberVO);
		if (image != null && !image.isEmpty()) {
			String fileName = bakeryVO.getCopRegNum() + "." + imageName.substring(imageName.lastIndexOf(".")+1);
			image.transferTo(new File("C://images/bakery/" + fileName));
			String path = "http://localhost:8080/images/bakery/" + fileName;
			bakeryVO.setBakeryPath(path);
		}
		bakeryRepository.save(bakeryVO);
	}
	
	public List<FoodVO> menuList(BakeryVO bakeryVO) {
		return foodRepository.findByBakeryVO(bakeryRepository.findById(bakeryVO.getCopRegNum()).get()).get();
	}
	
	public void addMenu(BakeryVO bakeryVO, FoodVO foodVO, MultipartFile image, String imageName) throws IOException {
		if (!image.isEmpty()) {
			int foodSeq;
			try {
			foodSeq = foodRepository.getFoodSeq().get() + 1;
			}catch (Exception e) {
				// TODO: handle exception
				foodSeq = 1;
			}
			String fileName = foodSeq + "." + imageName.substring(imageName.lastIndexOf(".")+1);
			image.transferTo(new File("C://images/food/" + fileName));
			String path = "http://localhost:8080/images/food/" + fileName;
			foodVO.setFoodPath(path);
		}
		bakeryVO = bakeryRepository.findById(bakeryVO.getCopRegNum()).get();
		foodVO.setBakeryVO(bakeryVO);
		foodRepository.save(foodVO);
	}

	public void modifyMenu(FoodVO foodVO, MultipartFile image, String imageName) throws IOException {
		if (image != null && !image.isEmpty()) {
			String fileName = foodVO.getFoodSeq() + "." + imageName.substring(imageName.lastIndexOf(".")+1);
			image.transferTo(new File("C://images/food/" + fileName));
			String path = "http://localhost:8080/images/food/" + fileName;
			foodVO.setFoodPath(path);
		}
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

	public List<FoodVO> menuViewList(BakeryVO bakeryVO) {
		return foodRepository.findByBakeryVO(bakeryVO).get();
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
