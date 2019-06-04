package com.cafe24.jblog.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	
	@Autowired
	private SqlSession session;
	
	public List<CategoryVo> selectCategoryListForMain(String id) {

		return session.selectList("category.selectCategoryListForMain", id);
	}
	
	public List<CategoryVo> selectCategoryList(String id) {

		return session.selectList("category.selectCategoryList", id);
	}

	public Long insertCategory(CategoryVo categoryVo) {
		Long categoryNo= (long) session.insert("category.insert", categoryVo);
		return categoryNo;
	}

	public int deleteCategory(Long categoryNo) {
		
		return session.delete("category.delete", categoryNo);
	}
}
