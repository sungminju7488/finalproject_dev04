package com.example.server.bakery;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.model.MemberVO;

@RestController
public class BakeryController {

	BakeryService bakeryService;
	
	@RequestMapping("/bakery/myShop")
	public void myShop(BakeryVO bakeryVO) {
		return bakeryService.myShop(bakeryVO);		
	}
	
	
	@RequestMapping("/bakery/changeBakery")
	public void update(BakeryVO bakeryVO) {
		return bakeryService.update(bakeryVO);
	}
	
	@RequestMapping("/bakery/menuList")
	public void menuList(BakeryVO bakeryVO) {
		return bakeryService.menuList(bakeryVO);
	}	
	
	@RequestMapping("/bakery/addMenu")
	public void addMenu(BakeryVO bakeryVO) {
		return bakeryService.addMenu(bakeryVO);
	}
	
	@RequestMapping("/bakery/modifyMenu")
	public void modifyMenu(BakeryVO bakeryVO) {
		return bakeryService.modifyMenu(bakeryVO);
	}
	
	@RequestMapping("/bakery/deleteMenu")
	public void deleteMenu(BakeryVO bakeryVO) {
		return bakeryService.deleteMenu(bakeryVO);
	}
	
	@RequestMapping("/bakery/boardToggle")
	public void boardToggle(BakeryVO bakeryVO) {
		return bakeryService.boardToggle(bakeryVO);
	}
}
