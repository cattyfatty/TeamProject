package com.mycompany.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsProjectController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsProjectController.class);
	
	@RequestMapping("/project/home")
	public String projectHome() {
		logger.info("project-home()");
		return "project/home";
	}
	
	@RequestMapping("/project/loginForm")
	public String projectLoginForm() {
		logger.info("project-login()");
		return "project/loginForm";
	}
	
	@RequestMapping("/project/login")
	public String projectLogin() {
		logger.info("project-login()");
		return "redirect:/project/home";
	}
	
	@RequestMapping("/project/joinForm")
	public String projectJoinForm() {
		logger.info("project-joinForm");
		return "project/joinForm";
	}
	
	@RequestMapping("/project/join")
	public String projectJoin() {
		logger.info("project-join");
		return "redirect:/project/home";
	}
	
	@RequestMapping("/project/productList")
	public String projectProductList() {
		logger.info("project-productList");
		return "project/productList";
	}
	
	
}
