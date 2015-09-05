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

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Goods;
import com.mycompany.myapp.service.GoodsService;

@Controller
public class GoodsProjectController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsProjectController.class);

	@Autowired
	private GoodsService goodsService;
	
	
	
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
	public String projectGoodsList(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("project-productList");
		session.setAttribute("pageNo", pageNo);
		
		
		int rowsPerPage = 10;
		int pagesPerGroup = 5;
		
		int totalBoardNo = goodsService.getTotalBoardNo();
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		
	List<Goods> goodslist = goodsService.getPage(pageNo, rowsPerPage);
		
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

	public String projectGoodsdetail(int goodsNo, Model model) {
		logger.info("detail()");
		
		Goods goods =goodsService.getGoods(goodsNo);
		model.addAttribute("goods", goods);
		return "project/goodsDetail";
		
	}

	
	@RequestMapping("/project/addCart")
	public String addCart(Goods goods,int amount,HttpSession session){
		logger.info("addCart()");
		
<<<<<<< HEAD
		Cart cart = new Cart();
=======
		Cart  cart = new Cart();
		cart.setcartAmount(amount);
		cart.setGoods_no(goods.getNo());
		//cart.setmemberId(session.getAttribute("loginId"));
>>>>>>> branch 'master' of https://github.com/cattyfatty/TeamProject.git
		
		if(cart!=null){
			goodsService.addCart(cart);	
		}
		
		return "redirect:/project/goodsList";
	}
	@RequestMapping("/project/cartList")
	public String projectShowCart(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("project-cartList");
		session.setAttribute("pageNo", pageNo);
		
		
		int rowsPerPage = 5;
		int pagesPerGroup = 3;
		
		int totalBoardNo = goodsService.getTotalBoardNo();
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		
		List<Cart> cartlist = goodsService.getCartPage(pageNo, rowsPerPage);
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("cartlist", cartlist);
		
		return "project/cartList";
	}
	
	
	
}
