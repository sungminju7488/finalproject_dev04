package com.example.server.bakery;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.server.model.MemberVO;

@Transactional
@Service
public class BakeryService {
	@Autowired
	BakeryRepository mr;
	
	public void joinBakery(BakeryVO bakeryVO) {
		mr.save(bakeryVO);
	}
	public Optional<BakeryVO> myshop(BakeryVO bakeryVO){
		return mr.findByCOPREGNUM(BakeryVO.getCOPREGNUM());
	}
	public String changeBakery(BakeryVO bakeryVO){
		mr.save(bakeryVO);
	}
	public Optional<BakeryVO>menuList(BakeryVO bakeryVO){
		mr.save(bakeryVO);
	}
	public void addMenu(BakeryVO bakeryVO) {
		mr.save(bakeryVO);
	}	
	public void modifyMenu(BakeryVO bakeryVO) {
		mr.save(bakeryVO);
	}
	public Optional<BakeryVO> deleteMenu(BakeryVO bakeryVO){
		mr.save(bakeryVO);
	}
	public void boardToggle(BakeryVO bakeryVO) {
		mr.save(bakeryVO);
	}}
