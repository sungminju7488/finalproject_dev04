package com.dev4.sunbbang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.PageVO;
import com.google.gson.Gson;

@Async
@RestController
public class BakeryController {

	@Autowired
	BakeryService bakeryService;
	
	@Autowired
	Gson gson;

	@RequestMapping("/bakery/searchBakery")
	public Object searchBakery(@RequestBody PageVO pageVO) {
		return gson.toJson(bakeryService.searchBakery(pageVO));
	}
	
	@RequestMapping("/bakery/setFollow")
	public void setFollow(@RequestBody AuthVO authVO, @RequestBody BakeryVO bakeryVO) {
		bakeryService.setFollow(authVO, bakeryVO);
	}
	
	@RequestMapping("/bakery/menuViewList")
	public Object menuViewList(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.menuViewList(bakeryVO));
	}
	
	@RequestMapping("/bakery/setAlarm")
	public void setAlarm(@RequestBody AuthVO authVO, FoodVO foodVO) {
		bakeryService.setAlarm(authVO, foodVO);
	}
	
	@RequestMapping("/bakery/searchFood")
	public Object searchFood(@RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.searchFood(foodVO));
	}
	
}
