package com.dev4.sunbbang.bakery;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/bakery/joinBakery")
	public ResponseToken joinBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName)
			throws IOException {
		AuthVO authVO = bakeryService.joinBakery(memberVO, bakeryVO, image, imageName);
		return new ResponseToken(jwtService.createToken(authVO));
	}

	@PostMapping("/bakery/myShop")
	public Object myShop(@RequestBody MemberVO memberVO) {
		return gson.toJson(bakeryService.myShop(memberVO));
	}

	@PostMapping("/bakery/changeBakery")
	public boolean changeBakery(MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName) {
		try {
			bakeryService.changeBakery(memberVO, bakeryVO, image, imageName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/bakery/menuList")
	public Object menuList(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.menuList(bakeryVO));
	}

	@PostMapping("/bakery/addMenu")
	public boolean addMenu(BakeryVO bakeryVO, FoodVO foodVO, MultipartFile image, String imageName) {
		try {
			bakeryService.addMenu(bakeryVO, foodVO, image, imageName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/bakery/modifyMenu")
	public boolean modifyMenu(BakeryVO bakeryVO, FoodVO foodVO, MultipartFile image, String imageName) {
		try {
			bakeryService.modifyMenu(bakeryVO, foodVO, image, imageName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("/bakery/deleteMenu")
	public boolean deleteMenu(@RequestBody FoodVO foodVO) {
		try {
			bakeryService.deleteMenu(foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/bakery/boardToggle")
	public Object boardToggle(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.boardToggle(bakeryVO));
	}

	@PostMapping("/bakery/searchBakery")
	public Object searchBakery(@RequestBody PageVO pageVO) {
		return gson.toJson(bakeryService.searchBakery(pageVO));
	}

//	@PostMapping("/bakery/setFollow")
//	public boolean setFollow(@RequestBody AuthVO authVO, @RequestBody BakeryVO bakeryVO) {
//		try {
//			bakeryService.setFollow(authVO, bakeryVO);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	@PostMapping("/bakery/menuViewList")
	public Object menuViewList(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.menuViewList(bakeryVO));
	}

	@PostMapping("/bakery/setAlarm")
	public ResponseToken setAlarm(AuthVO authVO, FoodVO foodVO, MultipartFile image) {
		return new ResponseToken(jwtService.createToken(bakeryService.setAlarm(authVO, foodVO)));
	}

	@PostMapping("/bakery/useAlarm")
	public Object useAlarm(@RequestBody AuthVO authVO) {
		System.out.println("Controller: useAlarm");
		List<FoodVO> list = bakeryService.useAlarm(authVO);
		return gson.toJson(list);
	}

	@PostMapping("/bakery/deleteAlarmApp")
	public Object deleteAlarmApp(@RequestBody MemberVO memberVO) {
		return gson.toJson(bakeryService.deleteAlarmApp(memberVO));
	}

//	@PostMapping("/bakery/deleteAlarm")
//	public Object deleteAlarm(@RequestBody AuthVO authVO, @RequestBody FoodVO foodVO) {
//		return gson.toJson(bakeryService.deleteAlarm(authVO, foodVO));
//	}

	@PostMapping("/bakery/searchFood")
	public Object searchFood(@RequestBody PageVO pageVO) {
		pageVO.setPageSize(12);
		return gson.toJson(bakeryService.searchFood(pageVO));
	}

}
