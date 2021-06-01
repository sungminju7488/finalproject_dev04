package com.dev4.sunbbang.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev4.sunbbang.member.MemberRepository;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.MemberVO;

@Transactional
@Service
public class BakeryService {

	@Autowired
	BakeryRepository br;

	@Autowired
	MemberRepository mr;

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

}
