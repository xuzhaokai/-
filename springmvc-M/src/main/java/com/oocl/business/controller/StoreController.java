package com.oocl.business.controller;

import javax.annotation.Resource;

import com.oocl.business.model.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.oocl.business.model.view.MerchantAuditView;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.Store;
import com.oocl.business.service.StoreService;

@Controller
@RequestMapping("/store")
@CrossOrigin(origins = "*", maxAge = 100000)
public class StoreController {

	@Resource(name = "storeService")
	private StoreService storeService;

	@ResponseBody
	@PostMapping(value = "/register")
	public RespondResult registerStore(@RequestBody MerchantAuditView regMsg) {

		RespondResult resp = storeService.registerStore(regMsg);
		return resp;
	}

	@ResponseBody
	@PutMapping(value = "/updateRegistration")
	public RespondResult updateRegistration(@RequestBody MerchantAuditView regMsg) {
		RespondResult resp = storeService.registerStore(regMsg);
		return resp;
	}


	@ResponseBody
	@PutMapping()
	public RespondResult update(@RequestBody Store store) {
		RespondResult resp =storeService.update(store);
		System.out.println(resp);
		return resp;

	}

	@ResponseBody
	@GetMapping("/{storeId}")
	public RespondResult listOne(@PathVariable String storeId) {
		System.out.println(storeId);
		return storeService.listOne(storeId);
	}


}