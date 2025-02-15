package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.myapp.entity.Member;
import com.smhrd.myapp.rapo.MemberRepository;
import com.smhrd.myapp.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberRepository repo;
	@Autowired
	private PasswordEncoder encoder; // -> SecurityConfiguration에 만들어놓음

	@PostMapping("/login")
	public String login(MemberVO vo, RedirectAttributes rttr) {

		Member entity = repo.findByUseridAndPw(vo.getUserid(), vo.getPw());
		
		System.out.println("???>>"+entity);
		if(entity!=null)
			return "redirect:/dashboard";
		
		return "redirect:/";
	}

	@PostMapping("/join-process")
	public String joinProcess(MemberVO vo) {

		// System.out.println("회원가입 받아온 값 >> " + vo);

		Member entity = new Member(vo, encoder);
		
		// userid와 pw값이 있어야 insert 진행
		if(vo.getUserid().length()!=0 || vo.getPw().length()!=0) {
			repo.save(entity);
		}
		
		return "redirect:/";
	}

}
