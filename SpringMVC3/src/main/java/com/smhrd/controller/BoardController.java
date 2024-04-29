package com.smhrd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;

@Controller
public class BoardController {

	@Autowired 
	private BoardMapper mapper;

	
	
	////////////////////////////게시물 페이지/////////////////////////////
	@RequestMapping("/") 
	public String board(Model model) {

		List<BoardVO> list = mapper.boardList();

		model.addAttribute("list", list);

		return "board";
	}

	
	
	
	////////////////////// 게시물 등록 //////////////////////////
	
	@GetMapping("/register") 
	public String register(Model model) {
		return "register";
	}

	// 글쓰기 기능을 하는 메소드
	@PostMapping("/register") 
	public String register(BoardVO vo) {

		mapper.boardRegister(vo);

		return "redirect:/";
	}
	
	
	
	//////////////////////// 게시물 한개에 대한 정보 ///////////////////////
	

	@GetMapping("/boardContent/{idx}")
	public String boardContent(@PathVariable("idx") int idx, Model model) {

		BoardVO list = mapper.boardContent(idx);

		model.addAttribute("list", list);

		return "boardContent";
	}
	
	
	
	
	
	///////////////////////// 게시물 삭제 ///////////////////////////
	
	@GetMapping("/delete/{idx}")
	public String delete(@PathVariable("idx") int idx) {

		mapper.delete(idx);

		return "redirect:/";
	}
	
	
	
	//////////////////////////// 게시물 수정 /////////////////////////
	
	
	@GetMapping("/edit/{idx}")
	public String edit(@PathVariable("idx") int idx, Model model) {

		BoardVO list = mapper.boardContent(idx);

		model.addAttribute("list", list);

		return "edit";
	}
	
	@PostMapping("/edit/{idx}")
	public String edit(@PathVariable("idx") int idx, BoardVO vo) {

		mapper.edit(vo);
		
		return "redirect:/boardContent/"+idx;
	}
	
	
	

}
