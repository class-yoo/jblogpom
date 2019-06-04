<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script type="text/javascript">

function removeCategory(categoryId) {
	
    $.ajax({
        type : 'GET',
        url : "${pageContext.request.contextPath}/category/api/remove",
        data : {"id" : categoryId},
        success : function (data) {
            if (data.data) {
            	$('#tr-'+categoryId).remove();
            	
            }else{
            	alert('다음에 다시 시도해주세요');
            }
        }
    });

}

function createCategory() {
	$.ajax({
        type : 'POST',
        url : "${pageContext.request.contextPath}/category/api/create",
        data : {"name" : $("#categoryName").val(), "description": $("#description").val()},
        success : function (data) {
        		var categoryNo = data.data.no;
            	var topCategoryIndex = parseInt(document.getElementsByClassName("td-category-count")[0].innerHTML)+1;
            	var categoryName = $('#categoryName').val();
            	var description = $('#description').val();
            	var postCount = 0;
            	
            	var categoryTr = 
            	'<tr id="tr-'+categoryNo+'">'+
					'<td>'+
						'<p class="td-category-count">'+topCategoryIndex+'</p>'+
					'</td>'+
					'<td>'+categoryName+'</td>'+
					'<td>'+postCount+'</td>'+
					'<td>'+description+'</td>'+
					'<td>'+
						'<img onclick="removeCategory('+categoryNo+')" src="${pageContext.request.contextPath}/assets/images/delete.jpg">'+
					'</td>'+
				'</tr>';
            $('#category-column').after(categoryTr);
            $('#categoryName').val('');
            $('#description').val('');
        }
    });
}
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-navigation.jsp"/>
		      	<table class="admin-cat" id="category-table">
		      		<tr id="category-column">
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${categoryList}" var="category" varStatus="status">
		      		<tr id="tr-${category.no}">
						<td>
						<p class="td-category-count">
						${categoryList.size()-status.count+1}
						</p>
						</td>
						<td>${category.name}</td>
						<td>${category.postCount}</td>
						<td>${category.description}</td>
						<td>
						<img onclick="removeCategory(${category.no})" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						</td>
					</tr>  
		      		</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="categoryName" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="description" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input onclick="createCategory()" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views//includes/blog-footer.jsp"/>
	</div>
</body>
</html>