package com.cafe24.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.security.Auth;
import com.cafe24.jblog.security.AuthUser;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.FileUploadService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;

@Controller   
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("/{id}/{categoryNo}/{postNo}")
	public String main(
			@PathVariable String id,
			@PathVariable Long categoryNo,
			@PathVariable Long postNo,
			Model model,
			HttpSession session) {
		
		if (id == null || "".equals(id)) {
			id = ((UserVo) session.getAttribute("authUser")).getId();
		}
		BlogVo blogVo = blogService.getBlog(id);
		
		if(blogVo == null) {
			return "redirect:/main";
		}
		
		List<CategoryVo> categoryList = blogService.getCategoriesForMain(id);
		if (categoryNo == -1 && categoryList.size() != 0) {
			categoryNo = categoryList.get(0).getNo();
		}
		
		List<PostVo> postList = blogService.getPosts(categoryNo);
		if (postNo == -1 && postList.size() != 0) {
			postNo = postList.get(0).getNo();
		}
		PostVo postVo = blogService.getPost(postNo);
		if(postVo == null) {
			postVo = new PostVo("빈카테고리입니다.","빈카테고리입니다.");
		}
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		return "blog/blog-main";

	}
	
	@Auth
	@RequestMapping("/admin/basic") // userId를 authUser를 보내서 사용하는게 낫나 Session에서 뽑아서 사용하는게 낫나 ?
	public String management(HttpSession session, Model model) {
		
//		service에서 사용자아이디를 이용해서 기본블로그 정보 불러오기
		String id = ((UserVo) session.getAttribute("authUser")).getId();
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}

	@RequestMapping("/modify")
	public String modify(HttpSession session,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "logoFile") MultipartFile multipartFile) {

		String saveFileName = fileUploadService.restore(multipartFile);
		String id = ((UserVo) session.getAttribute("authUser")).getId();
		BlogVo blogVo = new BlogVo(id, title, saveFileName);
		blogService.modify(blogVo);
		
		return "redirect:/blog/"+id+"/-1/-1";
	}
	
	
	@RequestMapping("/admin/category")
	public String category(HttpSession session, Model model) {
		
		String id = ((UserVo) session.getAttribute("authUser")).getId();
		
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryList = blogService.getCategories(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping("/admin/write")
	public String write(Model model, HttpSession session) {

		String id = ((UserVo) session.getAttribute("authUser")).getId();
		List<CategoryVo> categoryList = blogService.getCategoriesForMain(id);
		BlogVo blogVo = blogService.getBlog(id);
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("blogVo", blogVo);
		return "/blog/blog-admin-write";
		
	}
	
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(
			@ModelAttribute PostVo postVo,
				HttpSession session) {
		String id = ((UserVo) session.getAttribute("authUser")).getId();
		Long postNo = blogService.writePost(postVo);
		return "redirect:/blog/admin/write";
		
	}

}
