package com.oocl.business.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.oocl.business.model.Menu;
import com.oocl.business.model.RespondResult;
import com.oocl.business.service.MenuService;


@Controller
@RequestMapping("/menu")
@CrossOrigin(origins = "*", maxAge = 100000)
public class MenuController {

	@Resource(name = "menuService")
	private MenuService menuService;

	@ResponseBody
	@PostMapping("/{storeId}")
	public RespondResult add(@RequestBody Menu menu,@PathVariable String storeId) {
		return menuService.add(menu,storeId);

	}
	@ResponseBody
	@DeleteMapping("/{menuId}")
	public RespondResult delete(@PathVariable String menuId) {
		return menuService.delete(menuId);
	}
	@ResponseBody
	@GetMapping("/{storeId}")
	public RespondResult listAll(@PathVariable String storeId) {
		return menuService.findAll(storeId);

	}
	@ResponseBody
	@PutMapping("/{menuId}")
	public RespondResult update(@RequestBody Menu menu,@PathVariable String menuId) {
		return menuService.update(menu,menuId);

	}
	
	
}