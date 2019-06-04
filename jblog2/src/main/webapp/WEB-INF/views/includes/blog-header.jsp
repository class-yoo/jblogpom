<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	
	<h1>${blogVo.title}</h1>
	<c:choose>
		<c:when test='${empty authUser}'>
			<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/user/join">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/blog/admin/basic">나의 블로그 관리</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/blog/${authUser.getId()}/-1/-1">나의 블로그 홈</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/main">메인화면</a></li>
		</c:otherwise>
	</c:choose>
</div>