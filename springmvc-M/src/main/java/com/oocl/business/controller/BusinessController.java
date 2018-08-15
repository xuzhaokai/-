package com.oocl.business.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.oocl.business.model.Business;
import com.oocl.business.model.RespondResult;
import com.oocl.business.service.BusinessService;

@Controller
@RequestMapping("/business")
@CrossOrigin(origins = "*", maxAge = 100000)
public class BusinessController {

	@Resource(name = "businessService")
	private BusinessService businessService;

	@ResponseBody
	@GetMapping("/check")
	public RespondResult checkBusinessStatus( String account) {
		
		RespondResult resp = businessService.sendToAdminAndCreatStore(account);
		return resp;
	}

	@PostMapping("/login")
	@ResponseBody
	public RespondResult login( @RequestBody Business business,HttpServletRequest request) {
		RespondResult result = businessService.login(business);
		HttpSession session = request.getSession();
		if(result.getDatas()!=null){
			session.setAttribute("login", result.getDatas().get("business"));

		}

		return result;
	}

	@PostMapping("/register")
	@ResponseBody
	public RespondResult register(@RequestBody Business business) {
		return businessService.register(business);
	}


	@ResponseBody
	@GetMapping("/{storeId}")
	public RespondResult findComplaint(@PathVariable String storeId) {

		RespondResult resp = businessService.findComplaintFormA(storeId);
		return resp;
	}
}
