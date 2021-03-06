  package com.mycompany.myapp.controller;

import java.util.*;

import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.mycompany.myapp.dto.*;
import com.mycompany.myapp.service.*;

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
		logger.info("project-loginForm()");
		return "project/loginForm";
	}
	
	@RequestMapping("/project/login")
	public String projectLogin(Members member, HttpSession session) {
		logger.info("project-login()");
		Members loggedIn = goodservice.loginMember(member);
		if(loggedIn != null){
			session.setAttribute("member", loggedIn);
			return "redirect:/project/goodsList";
		}
		else{
			return "project/failLogin";
		}
	}
	
	@RequestMapping("/project/joinForm")
	public String projectJoinForm() {
		logger.info("project-joinForm");
		return "project/joinForm";
	}
	
	@RequestMapping("/project/join")
	public String projectJoin(Members member) {
		logger.info("project-join");
		goodservice.joinMember(member);
		return "redirect:/project/loginForm";
	}
	
	@RequestMapping("/project/goodsList")
	public String projectProductList(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("project-productList");
		
		
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
		model.addAttribute("goodslist", goodslist);
		
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
	public String orderlist(@RequestParam(defaultValue="1") int pageNo, HttpSession session, Model model) {
		
		session.setAttribute("pageNo", pageNo);
		Members mem = (Members) session.getAttribute("member");


		
		int rowsPerPage = 10;
		int pagesPerGroup = 5;
		
		int totalOrderNo = goodservice.getTotalOrderNo();
		
		int totalPageNo = totalOrderNo / rowsPerPage;
		if(totalOrderNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		List<Order> orderlist = goodservice.getOrderPage(pageNo, rowsPerPage, mem.getId());
		
		List<Members> memberlist = new ArrayList<Members>();
		for(Order order : orderlist){
			Members member = goodservice.getMembers(order.getMemberid());
			memberlist.add(member);
		}
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("orderlist", orderlist); 
		
		return "project/ordersList";

	
	}
	
	

	@RequestMapping("/project/addCart")

	public String addCart(Goods goods,int amount,HttpSession session){
	
 	
	Cart cart =new Cart();
	
	Members mem=(Members) session.getAttribute("member");
	cart.setMember_id(mem.getId());
	cart.setCart_amount(amount);
	cart.setGoods_no(goods.getNo());
	goodservice.addCart(cart);
	return "redirect:/project/goodsList";
	}
	
	
	@RequestMapping("/project/orderDetail")
	public String orderdetail(int orderNo, @RequestParam(defaultValue="1") int pageNo, HttpSession session, Model model) {
		
		int rowsPerPage = 10;
		int pagesPerGroup = 5;
		
		int totalOrderItemNo = goodservice.getTotalOrderItemNo();
		
		int totalPageNo = totalOrderItemNo / rowsPerPage;
		if(totalOrderItemNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		List<OrderItem> orderItem = goodservice.getOrderItemPage(orderNo, pageNo, rowsPerPage);
		
		List<Goods> goodslist = new ArrayList<Goods>();
		for(OrderItem item : orderItem){
			Goods goods = goodservice.getGoods(item.getGoodsItemNo());
			goodslist.add(goods);
		}
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("orderItem", orderItem);
		model.addAttribute("goodslist", goodslist);
		
		return "project/orderDetail";
		
	}

	
	@RequestMapping("/project/cartList")
	public String projectCartList(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("project-cartList");
		session.setAttribute("pageNo", pageNo);
		
		
		int rowsPerPage = 5;
		int pagesPerGroup = 3;
		
		int totalBoardNo = goodservice.getTotalBoardNo();
		Members mem = (Members) session.getAttribute("member");
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		
	List<Cart> cartList = goodservice.getCartPage(pageNo, rowsPerPage, mem.getId());
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("stotalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("cartList", cartList);
		
		List<Goods> goodsList = new ArrayList<>();
		for(Cart cart : cartList) {
			Goods goods = goodservice.getGoods(cart.getGoods_no());
			goodsList.add(goods);
		}
		model.addAttribute("goodsList", goodsList);
		
		return "project/cartList";                                    
	}
	@RequestMapping("/project/deleteCart")
	public String deleteCart(HttpSession session) {
		logger.info("deleteCart()");
		Members members = (Members) session.getAttribute("member");
		goodservice.deleteCart(members.getId());
		
		return "redirect:/project/cartList";
	}
	
	@RequestMapping("/project/addOrder")
	public String addOrder(HttpSession session,Model model){
		logger.info("addOrder()");
		
		Cart cart = new Cart();
		Members members = (Members) session.getAttribute("member");
		cart.setMember_id(members.getId());
		model.addAttribute("cart", cart);
		goodservice.addOrder(members.getId());
		
		return "redirect:/project/orderList";
	}
	

	@RequestMapping("/project/logout")
	public String logout(HttpSession session){
		logger.info("logout()");
		session.invalidate();
		return "redirect:/";
	}
}
