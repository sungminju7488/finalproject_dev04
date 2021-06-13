package com.dev4.sunbbang.bakery;

import java.io.IOException;
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
import com.dev4.sunbbang.util.JwtService;
import com.dev4.sunbbang.util.ResponseToken;
import com.google.gson.Gson;

@Async
@RestController
public class BakeryController {

	@Autowired
	BakeryService bakeryService;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	Gson gson;

	@RequestMapping("/bakery/joinBakery")
	public ResponseToken joinBakery(MemberVO memberVO, BakeryVO bakeryVO, 
			MultipartFile image, String imageName) throws IOException {
			AuthVO authVO = bakeryService.joinBakery(memberVO, bakeryVO, image, imageName);
			return new ResponseToken(jwtService.createToken(authVO));
	}

	@RequestMapping("/bakery/myShop")
	public Object myShop(@RequestBody MemberVO memberVO) {
		return gson.toJson(bakeryService.myShop(memberVO));
	}
	
	@RequestMapping("/bakery/changeBakery")
	public boolean changeBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName) {
		try {
			bakeryService.changeBakery(memberVO, bakeryVO, image, imageName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("error : " + );
			return false;
		}
	}
	
	@RequestMapping("/bakery/menuList")
	public Object menuList(@RequestBody MemberVO memberVO) {
		return gson.toJson(bakeryService.menuList(memberVO));
	}

	@RequestMapping("/bakery/addMenu")
	public boolean addMenu(@RequestBody BakeryVO bakeryVO, @RequestBody FoodVO foodVO) {
		try {
			bakeryService.addMenu(bakeryVO, foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/modifyMenu")
	public boolean modifyMenu(@RequestBody FoodVO foodVO) {
		try {
			bakeryService.modifyMenu(foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/deleteMenu")
	public boolean deleteMenu(@RequestBody FoodVO foodVO) {
		try {
			bakeryService.deleteMenu(foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/boardToggle")
	public Object boardToggle(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.boardToggle(bakeryVO));
	}

	@RequestMapping("/bakery/searchBakery")
	public Object searchBakery(@RequestBody PageVO pageVO) {
		return gson.toJson(bakeryService.searchBakery(pageVO));
	}

	@RequestMapping("/bakery/setFollow")
	public boolean setFollow(@RequestBody AuthVO authVO, @RequestBody BakeryVO bakeryVO) {
		try {
			bakeryService.setFollow(authVO, bakeryVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/menuViewList")
	public Object menuViewList(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.menuViewList(bakeryVO));
	}

	@RequestMapping("/bakery/setAlarm")
	public boolean setAlarm(@RequestBody AuthVO authVO, @RequestBody FoodVO foodVO) {
		try {
			bakeryService.setAlarm(authVO, foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
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
