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
import org.springframework.web.client.HttpServerErrorException;

import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;

// Spring F/W 흐름
// (1) Dispatcher Servlet(Front Controller) 요청을 받는다
// (2) Handler Mapping 에게 요청값(요청url)을 보낸다 (메서드 단위로 url매핑)
// (3) Handler Mapping이 요청값과 일치하는 Controller를 Spring Container에서 찾는다
// (4) Handler Adapter가 HM이 찾은 Controller를 실행

// (3)을 위한 구문(SC(Spring Controller)에서도 쓸수있게 하기)
// 해당하는 클래스 파일이 SC 안에 자동으로 생성될 수 있게 만들고,
// Controller임을 명시하는 어노테이션
@Controller // 기능이 정의 되어있음
public class BoardController {

	@Autowired // 중요*****************
	private BoardMapper mapper;
	// Autowired
	// : 자동으로 연결해줘라는 의미
	// : Spring Container에서 mapper를 구현한 구현체를 자동으로 연결해줌

	// Spring F/W는 url mapping값을 메소드 단위로 설정함
	// RequestMapping() --> url mapping값을 잡아주는 방법
	@RequestMapping("/") // "/"로 요청이 들어왔을 때 board 메소드를 실행
	public String board(Model model) {
		// Model을 가져오는 방법
		// --> 메소드의 매개변수로 해당하는 객체를 작성
		// --> SC가 자동으로 객체를 채워줌

		// 2. DAO 사용해서 결과값 받아오기
		List<BoardVO> list = mapper.boardList();

		// 3. 받아온 결과값 model 영역에 담아주기
		// model이란? -> request의 경량화된 버전
		model.addAttribute("list", list);

		// 4. board.jsp로 forward 방식으로 이동하기

		return "board";
		// board --> 논리적인 주소값
		// /WEB-INF/views/board.jsp --> 물리적인 주소값
		// 논리적인 주소값을 물리적인 주소값으로 자동으로 변환해주는 View Resolver 객체가 이미 존재
		// ViewResolver의 역할은? (servlet-context.xml파일에 있음)
		// WER-INF/views/ + 리턴한 값 + .jsp
	}

	
	
	
	////////////////////// 게시물 등록 //////////////////////////
	
	// 글쓰기 페이지로 이동하는 메소드 (post방식)
	@GetMapping("/register") // -->> post 전송방식 일때만 메소드 실행
	public String register(Model model) {
		return "register";
	}

	// 글쓰기 기능을 하는 메소드
	@PostMapping("/register") // -->> get 전송방식 일때만 메소드 실행
	public String register(BoardVO vo) {

		// 요청데이터 수집 -> Spring이 알아서 해줌
		System.out.println(vo);
		// 주소값 안나오고 안에 저장되어있는 데이터 출력
		// --> @Data >> getter, setter, toString method override
		// toString method?
		// >> 객체의 주소값을 출력하는 대신, 안쪽에 있는 데이터 출력을 도와주는 object 메소드

		mapper.boardRegister(vo);

		// board.jsp페이지로 갈때 board 메서드가 실행돼야하기 때문에
		return "redirect:/";
	}
	
	
	
	//////////////////////// 게시물 한개에 대한 정보 ///////////////////////
	
	

	// "/boardContent/{idx}"의 idx를 쓰기위해 @PathVariable("idx") int idx로 선언
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
