package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.dao.BlogDao;
import com.cafe24.jblog.repository.dao.CategoryDao;
import com.cafe24.jblog.repository.dao.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public boolean createUser(UserVo userVo) {
		BlogVo blogVo = new BlogVo(userVo.getId(), "블로그 제목을 입력해주세요.", "" );
		CategoryVo categoryVo = new CategoryVo("미분류", "카테고리를 지정하지 않은경우", userVo.getId());
			
		return 1== userDao.insertUser(userVo) && 1== blogDao.insertBlog(blogVo) && 1== categoryDao.insertCategory(categoryVo);
	}

	public Boolean existId(String id) {
		return userDao.existId(id) != null;
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.selectUser(userVo);
	}

}
