package com.dev4.sunbbang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;

public interface FoodRepository extends JpaRepository<FoodVO, Integer>{
	
	public Optional<List<FoodVO>> findByBakeryVO(BakeryVO bakeryVO);
	public Optional<List<FoodVO>> findByFoodName(String foodName);
	
	@Query(value="SELECT FOOD_SEQ.CURRVAL FROM DUAL", nativeQuery = true)
	public Optional<Integer> getFoodSeq();

}
