package com.dev4.sunbbang.bakery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.model.PageVO;
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
	public void deleteMenu(@RequestBody FoodVO foodVO) {
		bakeryService.deleteMenu(foodVO);
	}

	@RequestMapping("/bakery/boardToggle")
	public void boardToggle(@RequestBody BakeryVO bakeryVO) throws Exception {
		bakeryService.boardToggle(bakeryVO);

	}
	
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
	public void setAlarm(@RequestBody AuthVO authVO, @RequestBody FoodVO foodVO) {
		bakeryService.setAlarm(authVO, foodVO);
	}
	
	@RequestMapping("/bakery/useAlarm")
	public Object useAlarm(@RequestBody AuthVO authVO) {
		System.out.println("Controller: useAlarm");
		List<FoodVO> list = bakeryService.useAlarm(authVO);
		return gson.toJson(list);
	}
	
	@RequestMapping("/bakery/deleteAlarm")
	public Object deleteAlarm(@RequestBody AuthVO authVO, @RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.deleteAlarm(authVO, foodVO));
	}
	
	@RequestMapping("/bakery/searchFood")
	public Object searchFood(@RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.searchFood(foodVO));
	}

}
