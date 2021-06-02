package com.dev4.sunbbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev4.sunbbang.bakery.BakeryRepository;
import com.dev4.sunbbang.bakery.FoodRepository;
import com.dev4.sunbbang.member.MemberRepository;
import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.PageVO;

@Transactional
@Service
public class BakeryService {

	@Autowired
	BakeryRepository br;

	@Autowired
	MemberRepository mr;
	
	@Autowired
	FoodRepository fr;

	public Page<BakeryVO> searchBakery(PageVO pageVO) {
		return br.findByStoreNameContaining(pageVO.getKeyword(),
				PageRequest.of(pageVO.getPagaNo(), pageVO.getPageSize())).get();
	}

	public void setFollow(AuthVO authVO, BakeryVO bakeryVO) {
		String followSet = mr.findById(authVO.getMemberSeq()).get().getFollowSet() + bakeryVO.getMemberSeq() + ",";
		mr.modifyToFollowSet(followSet, authVO.getMemberSeq());
	}
	
	public List<FoodVO> menuViewList(BakeryVO bakeryVO){
		return fr.findByBakerySeq(bakeryVO.getMemberSeq()).get();
	}
	
	public void setAlarm(AuthVO authVO, FoodVO foodVO) {
		String alarmSet = mr.findById(authVO.getMemberSeq()).get().getAlarmSet() + foodVO.getFoodSeq() + ",";
		mr.modifyToAlarmSet(alarmSet, authVO.getMemberSeq());
	}
	
	public List<FoodVO> searchFood(FoodVO foodVO){
		return fr.findByFoodName(foodVO.getFoodName()).get();
	}
}
