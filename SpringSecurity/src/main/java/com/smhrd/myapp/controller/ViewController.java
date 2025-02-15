package com.smhrd.myapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/")
	private String login() {
		return "login";
	}
	
	@GetMapping("/join")
	private String join() {
		return "join";
	}
	
	@GetMapping("/user")
	private String user() {
		return "user";
	}
	
	@GetMapping("/dashboard")
	private String dashboard() {
		return "dashboard";
	}
	
	@GetMapping("/admin")
	// @PreAuthorize("hasAnyRole('admin')")
	private String admin() {
		return "admin";
	}
	
	
}
