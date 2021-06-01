package com.dev4.sunbbang.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.BakeryVO;
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

	public void changeBakery(@RequestBody BakeryVO bakeryVO) {
		bakeryService.myShop(bakeryVO);

	}
}
