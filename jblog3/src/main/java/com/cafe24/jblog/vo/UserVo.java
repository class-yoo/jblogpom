package com.cafe24.jblog.vo;

public class UserVo {
	private Long no;
	private String id;
	private String name;
	private String password;
	private String regDate;

	public UserVo() {
	}
	
	public UserVo(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
