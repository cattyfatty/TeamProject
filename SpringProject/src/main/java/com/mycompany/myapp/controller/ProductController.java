package com.mycompany.myapp.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myapp.dto.Product;
import com.mycompany.myapp.service.ProductService;

@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		logger.info("list()");

		int rowsPerPage = 10;
		int pagesPerGroup = 5;

		session.setAttribute("pageNo", pageNo);
		
		// 전체 게시물 수
		int totalBoardNo = productService.getTotalProductNo();

		// 전체 페이지 수
		int totalPageNo = totalBoardNo / rowsPerPage;
		if (totalBoardNo % rowsPerPage > 0) {
			totalPageNo += 1;
		}

		// 전체 그룹 수
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if (totalPageNo % pagesPerGroup > 0) {
			totalGroupNo += 1;
		}

		// 현제 그룹 번호, 시작 페이지 번호, 끝 페이지 번호
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if (groupNo == totalGroupNo) {
			endPageNo = totalPageNo;
		}

		List<Product> list = productService.getPage(pageNo, rowsPerPage);
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("list", list);

		return "product/list";
	}

	@RequestMapping("/product/writeForm")
	public String writeForm() {
		logger.info("writeForm()");

		return "product/writeForm";
	}

	@RequestMapping("/product/write")
	public String write(Product product, HttpSession session) {
		logger.info("write()");
		
		String dirPath = null;
		String originalFileName = null;
		String filesystemName = null;
		String contentType = null;
		if(!product.getPhoto().isEmpty()) {
			// 파일 정보 얻기
			ServletContext application = session.getServletContext();
			dirPath = application.getRealPath("/resources/uploadfiles");
			originalFileName = product.getPhoto().getOriginalFilename();
			filesystemName = System.currentTimeMillis() + "-" + originalFileName;
			contentType = product.getPhoto().getContentType();
			
			// 파일 저장하기
			try {
				product.getPhoto().transferTo(new File(dirPath + "/" + filesystemName));
			} catch (Exception e) {	e.printStackTrace(); }
		}

		if(!product.getPhoto().isEmpty()) {
			product.setOriginalFileName(originalFileName);
			product.setFilesystemName(filesystemName);
			product.setContentType(contentType);
		}

		productService.add(product);

		return "redirect:/product/list";
	}

	@RequestMapping("/product/detail")
	public String detail(int productNo, Model model) {
		logger.info("detail()");
		
		Product product = productService.getProduct(productNo);
		model.addAttribute("product", product);
		
		return "product/detail";
	}
	
	@RequestMapping("/product/updateForm")
	public String updateForm(int productNo, Model model) {
		logger.info("updateForm()");
		
		Product product = productService.getProduct(productNo);
		model.addAttribute("product", product);
		
		return "product/update";
	}
	
	@RequestMapping("/product/update")
	public String update(Product product, Model model) {
		logger.info("update()");
		
		productService.modify(product);
		
		return "redirect:/product/detail?productNo" + product.getNo();
	}
}
