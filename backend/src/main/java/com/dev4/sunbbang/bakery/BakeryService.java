package com.dev4.sunbbang.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev4.sunbbang.member.MemberRepository;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;

@Transactional
@Service
public class BakeryService {

	@Autowired
	BakeryRepository br;

	@Autowired
	MemberRepository mr;

	@Autowired
	FoodRepository fd;

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

	public BakeryVO menuList(FoodVO foodVO) {
		return fd.findByBakerySeq(foodVO.getBakerySeq()).get();

	}

	public void addMenu(FoodVO foodVO) {
		fd.save(foodVO);
	}

	public void modifyMenu(FoodVO foodVO) {
		fd.save(foodVO);
	}

	public void deleteMenu(FoodVO foodVO) {
		fd.delete(foodVO);
	}

	public void boardToggle(@RequestBody BakeryVO bakeryVO) throws Exception {
		if(bakeryVO.getBoardSet()==null);
		
		
	
	}

		
	}


