package com.cafe24.jblog.repository.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		
		return sqlSession.insert("user.insert", userVo);
	}

	public String existId(String id) {
		
		return sqlSession.selectOne("user.checkExistId", id);
	}

	public UserVo selectUser(UserVo userVo) {
		
		return sqlSession.selectOne("user.select", userVo);
	}

}
