package com.dev4.sunbbang.bakery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;

public interface FoodRepository extends JpaRepository<FoodVO, Integer>{
	
	

	public Optional<BakeryVO> findByBakerySeq(int bakerySeq);


}
