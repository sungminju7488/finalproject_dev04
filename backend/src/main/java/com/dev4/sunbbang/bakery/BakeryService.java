package com.dev4.sunbbang.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
	BakeryRepository br;

	@Autowired
	MemberRepository mr;

	@Autowired
	FoodRepository fr;

	public void joinBakery(MemberVO memberVO, BakeryVO bakeryVO) {
		mr.save(memberVO);
		br.save(bakeryVO);
	}

	public BakeryVO myShop(BakeryVO bakeryVO) {
		return br.findByMemberSeq(bakeryVO.getMemberSeq()).get();
	}

	public void changeBakery(BakeryVO bakeryVO) {
		br.save(bakeryVO);
	}

	public List<FoodVO> menuList(FoodVO foodVO) {
		return fr.findByBakerySeq(foodVO.getBakerySeq()).get();
	}

	public void addMenu(FoodVO foodVO) {
		fr.save(foodVO);
	}

	public void modifyMenu(FoodVO foodVO) {
		fr.save(foodVO);
	}

	public void deleteMenu(FoodVO foodVO) {
		fr.delete(foodVO);
	}

	public void boardToggle(@RequestBody BakeryVO bakeryVO) throws Exception {
//		if (bakeryVO.getBoardSet() == null)
//			;

	}
	
	public Page<BakeryVO> searchBakery(PageVO pageVO) {
		return br.findByStoreNameContaining(pageVO.getKeyword(),
				PageRequest.of(pageVO.getPagaNo(), pageVO.getPageSize())).get();
	}

	public void setFollow(AuthVO authVO, BakeryVO bakeryVO) {
		String followSet = authVO.getFollowSet() + bakeryVO.getMemberSeq() + ",";
		mr.modifyToFollowSet(followSet, authVO.getMemberSeq());
	}
	
	public List<FoodVO> menuViewList(BakeryVO bakeryVO){
		return fr.findByBakerySeq(bakeryVO.getMemberSeq()).get();
	}
	
	public void setAlarm(AuthVO authVO, FoodVO foodVO) {
		String alarmSet = authVO.getAlarmSet() + foodVO.getFoodSeq() + ",";
		mr.modifyToAlarmSet(alarmSet, authVO.getMemberSeq());
	}
	
	public List<FoodVO> useAlarm(AuthVO authVO){
		StringTokenizer st = new StringTokenizer(authVO.getAlarmSet()); 
		List<FoodVO> list = new ArrayList<FoodVO>();
		while(st.hasMoreTokens()) {
			list.add(new FoodVO(Integer.parseInt(st.nextToken(","))));
		}
		for(FoodVO foodVO:list) {
			foodVO = fr.findById(foodVO.getFoodSeq()).get();
		}
		return list;
	}
	
	public List<FoodVO> searchFood(FoodVO foodVO){
		return fr.findByFoodName(foodVO.getFoodName()).get();
	}

}
