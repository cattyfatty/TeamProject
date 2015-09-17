  package com.mycompany.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.service.GoodsService;

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Goods;
import com.mycompany.myapp.dto.Orders;

@Controller
public class GoodsProjectController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsProjectController.class);

	@Autowired
	private GoodsService goodservice;
	
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
	
	@RequestMapping("/project/goodsList")
	public String projectProductList(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("project-productList");
		session.setAttribute("pageNo", pageNo);
		
		
		int rowsPerPage = 10;
		int pagesPerGroup = 5;
		
		int totalBoardNo = goodservice.getTotalBoardNo();
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		session.setAttribute("pageNo", pageNo);
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		
	List<Goods> goodslist = goodservice.getPage(pageNo, rowsPerPage);
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("list", goodslist);
		
		return "project/goodsList";                                    
	}
	
	@RequestMapping("/project/goodsDetail")

	public String projectProductdetail(int goodsNo, Model model) {
		logger.info("detail()");
		
		Goods goods =goodservice.getGoods(goodsNo);
		model.addAttribute("goods", goods);
		return "project/goodsDetail";
		
	}

	
	@RequestMapping("/project/orderList")
	public String orderlist(String memberId, @RequestParam(defaultValue="1") int pageNo, HttpSession session, Model model) {
		
		session.setAttribute("pageNo", pageNo);

		List<Orders> orderlist = goodservice.getOrders(memberId);
		
		model.addAttribute("orderlist", orderlist);
		
		int rowsPerPage = 10;
		int pagesPerGroup = 5;
		
		int totalBoardNo = goodservice.getTotalBoardNo();
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		//List<Orders> orderlist = goodservice.getOrderPage(pageNo, rowsPerPage);
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("orderlist", orderlist); 
		
		return "project/orderList";

	
	}
	
	
	
	@RequestMapping("/project/addCart")
	public String addCart(int amount,Goods goods,HttpSession session){
		Cart cart = new Cart();
		cart.setcartAmount(amount);
		cart.setGoods_no(goods.getNo());
		//cart.setmemberId(session.getAttribute("loginId"));
		goodservice.addCart(session.getAttribute("memberId").toString(),amount,goods.getNo());
		
		return "redirect:/project/goodList";
	}
	
	@RequestMapping("/project/orderdetail")

	public String orderdetail(String memberId, Model model) {
		
		List<Orders> list = goodservice.getOrders(memberId);
		
		
		model.addAttribute("list", list);
		
		return "project/orderList";
		
	}
	@RequestMapping("/project/cartList")
	public String projectCartList(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("project-cartList");
		session.setAttribute("pageNo", pageNo);
		
		
		int rowsPerPage = 5;
		int pagesPerGroup = 3;
		
		int totalBoardNo = goodservice.getTotalBoardNo();
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		
	List<Cart> cartList = goodservice.getCartPage(pageNo, rowsPerPage);
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("cartlist", cartList);
		
		return "project/cartList";                                    
	}
}