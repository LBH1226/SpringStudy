package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.myapp.entity.MemberEntity;
import com.smhrd.myapp.mapper.MemberMapper;
import com.smhrd.myapp.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;

   // index.jsp를 띄워주는 메소드
   // @RequestMapping() 보다 Springboot에서는 @Get @Post Mapping 권장
   @GetMapping("/")
   public String index() {
      return "index";
   }
   
   @GetMapping("/mypage")
   public String mypage() {
	   return "mypage";
   }
   
   @GetMapping("/join")
   public String join() {
	   return "join";
   }
 
///////////////////////////Session쓰는 방법////////////////////////////////////////
//   @PostMapping("/login")
//   public String login(MemberVO vo, HttpSession session ) {
//	   
//	   // mapper.login(vo);
//	   
//	   // JAP (mapper == repository)
//	   MemberEntity entity =  mapper.findByIdAndPw(vo.getId(), vo.getPw());
//	   // entity(JPA용) --> vo(java, jsp용)로 변경
//	   System.out.println("받아온 entity >> "+entity);
//	   
//	   MemberVO myData = new MemberVO(entity.getId(), entity.getPw(), entity.getName(), entity.getTel());
//	   // Model : request 경량화 버전
//	   // --> 응답을 되돌려주기 전까지만 유효
//	   
//	   session.setAttribute("myData", myData);
//	   // --> 브라우저 닫히기 전까지 유효
//	   
//	   
//	   
//	   return "redirect:/mypage";
//	   // 요청2, 응답2
//   }

/////////////////////////// RedirectAttributes사용법 ////////////////////////////////
   @PostMapping("/login")
   public String login(MemberVO vo, RedirectAttributes rttr ) {
	   
	   MemberEntity entity =  mapper.findByIdAndPw(vo.getId(), vo.getPw());
	   MemberVO myData = new MemberVO(entity.getId(), entity.getPw(), entity.getName(), entity.getTel());
	   
	   rttr.addAttribute("myData", myData.getId());
	   // *주의할 점 : 들어가는 value의 형태를 String으로 변환 가능한 형태로 넣어주기
	   // redirect:/mypage?myData=?? 형식
	   // /login 페이지와 /mypage페이지까지 유효한 데이터
	   
	   return "redirect:/mypage";
   }
   
   @PostMapping("/join-process")
   public String joinProcess(MemberVO vo) {
	   
	   System.out.println("ddddddd >> "+vo);
	   
	   MemberEntity entity = new MemberEntity(vo);
	   mapper.save(entity);
	   // update sql구문도 save 처리
	   // save(entity)
	   // --> entity안에 idx가 없으면, insert진행
	   // --> entity안에 idx가 채워져있으면, update 진행
	   
	   
	  
	   return "redirect:/";
   }

   
}
