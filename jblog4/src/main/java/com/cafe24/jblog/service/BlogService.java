package com.cafe24.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.dao.BlogDao;
import com.cafe24.jblog.repository.dao.CategoryDao;
import com.cafe24.jblog.repository.dao.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

import javafx.geometry.Pos;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public boolean modify(BlogVo blogVo) {
		return 1 == blogDao.update(blogVo);
	}

	public BlogVo getBlog(String id) {
		return blogDao.selectBlog(id);
	}

	public List<CategoryVo> getCategoriesForMain(String id) {
		
		return categoryDao.selectCategoryListForMain(id);
		
	}

public List<CategoryVo> getCategories(String id) {
		
		return categoryDao.selectCategoryList(id);
		
	}
	
	public Long writePost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}

	public PostVo getPost(Long postNo) {
		
		return postDao.selectPost(postNo);
	}
	
	public List<PostVo> getPosts(Long categoryNo) {
		
		return postDao.selectPosts(categoryNo);
	}

	public Boolean removeCategory(Long categoryNo) {
		
		postDao.deletePost(categoryNo);
		return 1== categoryDao.deleteCategory(categoryNo);
	}

	public Long createCategory(CategoryVo categoryVo) {
		Long categoryNo = categoryDao.insertCategory(categoryVo);
		return categoryNo;
	}


}
