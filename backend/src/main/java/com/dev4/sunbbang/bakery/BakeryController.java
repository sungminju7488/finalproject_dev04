package com.dev4.sunbbang.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;
import com.google.gson.Gson;

@Async
@RestController
public class BakeryController {

	@Autowired
	BakeryService bakeryService;

	@Autowired
	Gson gson;

	@RequestMapping("/bakery/joinBakery")
	public void joinBakery(@RequestBody MemberVO memberVO, BakeryVO bakeryVO) {
		bakeryService.joinBakery(memberVO, bakeryVO);
	}

	@RequestMapping("/bakery/myShop")
	public Object myShop(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.myShop(bakeryVO));
	}

	@RequestMapping("/bakery/changeBakery")
	public void changeBakery(@RequestBody BakeryVO bakeryVO, MultipartFile image) {
		bakeryService.myShop(bakeryVO);

	}

	@RequestMapping("/bakery/menuList")
	public Object menuList(@RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.menuList(foodVO));

	}
	
	@RequestMapping("/bakery/addMenu")
	public void addMenu(@RequestBody FoodVO foodVO) {
		bakeryService.addMenu(foodVO);
	}
	
	@RequestMapping("/bakery/modifyMenu")
	public void modifyMenu(@RequestBody FoodVO foodVO) {
		bakeryService.modifyMenu(foodVO);
	}
	
	@DeleteMapping("/bakery/deleteMenu")
	public Object deleteMenu(@RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.deleteMenu(foodVO));
	}
	
}
