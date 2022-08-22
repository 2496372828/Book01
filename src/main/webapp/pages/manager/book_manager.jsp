<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

	<!--静态包含base标签，css样式，jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteClass").click(function () {
				return (confirm("确定要删除"+$(this).parent().parent().find("td:first").text()+"?"));
				
			})

		})

	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<!--静态包含图书管理菜单-->
			<%@include file="/pages/common/manager_menu.jsp"%>
		
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="${requestScope.page.url}?action=getBook&id=${book.id}&method=updateBook&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="${requestScope.page.url}?action=deleteBookById&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=addBook&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>

		</table>

		<%--静态包含分页条--%>
		<%@include file="/pages/common/pagination.jsp"%>
	</div>


	<!--静态包含页脚内容-->
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>