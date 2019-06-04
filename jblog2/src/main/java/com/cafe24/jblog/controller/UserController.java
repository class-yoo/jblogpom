package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/join")
	public String joinform() {
		return "user/join";
	}
	
	@RequestMapping(value= "/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		
		userService.createUser(userVo);
		
		return "user/joinsuccess";
		
	}
	
	@RequestMapping("/login")
	public String loginform() {
		return "user/login";
	}
	
}