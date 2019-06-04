package com.cafe24.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private int postCount;
	private String description;
	private String regDate;
	private String blogId;

	public CategoryVo() {
	}

	public CategoryVo(String name, String description, String blogId) {
		this.name = name;
		this.description = description;
		this.blogId = blogId;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

}
