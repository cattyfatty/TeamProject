package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myapp.dto.Board;
import com.mycompany.myapp.service.BoardService;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/list")
	public String list(Model model, @RequestParam(defaultValue="1") int pageNo, HttpSession session) {
		logger.info("list()");
		
		session.setAttribute("pageNo", pageNo);
		
		int rowsPerPage = 10;
		int pagesPerGroup = 5;
		
		int totalBoardNo = boardService.getTotalBoardNo();
		
		int totalPageNo = totalBoardNo / rowsPerPage;
		if(totalBoardNo % rowsPerPage > 0) { totalPageNo += 1; }
		
		int totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup > 0) { totalGroupNo += 1; }
		
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) { endPageNo = totalPageNo; }
		
		List<Board> list = boardService.getPage(pageNo, rowsPerPage);
		
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	@RequestMapping("/board/writeForm")
	public String writeForm() {
		logger.info("writeForm()");
		return "board/writeForm";
	}
	
	@RequestMapping("/board/write")
	/*public String write(String title, String writer, String content, @RequestParam(defaultValue="") MultipartFile attach, HttpSession session) {*/
	public String write(Board board, HttpSession session) {
		logger.info("write()");
		String dirPath = null;
		String originalFileName = null;
		String filesystemName = null;
		String contentType = null;
		if(!board.getAttach().isEmpty()) {
			ServletContext application = session.getServletContext();
			dirPath = application.getRealPath("/resources/uploadfiles");
			originalFileName = board.getAttach().getOriginalFilename();
			filesystemName = System.currentTimeMillis() + "-" + originalFileName;
			contentType = board.getAttach().getContentType();
			
			try {
				board.getAttach().transferTo(new File(dirPath + "/" + filesystemName));
			} catch (Exception e) {	e.printStackTrace(); }
			
		}
		
		if(!board.getAttach().isEmpty()) {
			board.setOriginalFileName(originalFileName);
			board.setFilesystemName(filesystemName);
			board.setContentType(contentType);
		}
		boardService.add(board);
		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/updateForm")
	public String updateForm(int boardNo, Model model) {
		logger.info("updateForm()");
		
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		
		return "board/updateForm";
	}
	
	@RequestMapping("/board/update")
	public String update(Board board, Model model) {
		logger.info("update()");
		
		boardService.modify(board);
		
		return "redirect:/board/detail?boardNo=" + board.getNo();
	}
	
	@RequestMapping("/board/detail")
	public String detail(int boardNo, Model model) {
		logger.info("detail()");
		
		boardService.addHitcount(boardNo);
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	
	@RequestMapping("/board/delete")
	public String delete(int boardNo) {
		logger.info("delete()");
		
		boardService.remove(boardNo);
		
		return "redirect:/board/list";
	}
}
