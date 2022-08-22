<%@ page import="com.sdjtxy.pojo.Cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<!--静态包含base标签，css样式，jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	<script type="text/javascript">
		$(function (){
			$(".book_id_count_update").change(function (){
				//当数量框发生改变时：
				var name=$(this).parent().parent().find("td:first").text();
				var id=$(this).attr("bookId");
				var count=this.value;
				var defaultValue=this.defaultValue;

				if(confirm("是否要修改图书《"+name+"》的数量由"+defaultValue+"为:"+count)){
					//确认则修改，将请求发送到Servlet程序中

					location.href="http://localhost:8080/Book01/cartServlet?action=updateCount&id="+id+"&count="+count;
				}else {
					//取消修改则将商品数量还原---dom对象中text的defaultValue即更改之前的值
					this.value=this.defaultValue;
				}

			})
		})
	</script>


	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

			<!--静态包含登录之后的菜单-->
			<%@include file="/pages/common/login_success_menu.jsp"%>>

	</div>

	<c:if test="${ empty sessionScope.cart.items}">

		<div id="main">
			<div class="cart_info">
				<span class="cart_span">购物车中空空如也</span><br>
				<span class="cart_span">请快快去购物吧！！！</span><br>
				<a href="index.jsp">返回首页</a>

			</div>

		</div>

	</c:if>

	<c:if test="${not empty sessionScope.cart.items}">
		<div id="main">

			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${sessionScope.cart.items}" var="book">
					<tr>
						<td>${book.value.name}</td>
						<td>
								<%--对商品数量的修改--%>
							<input class="book_id_count_update" type="text"
								bookId="${book.key}"
								value="${book.value.count}"
							>
						</td>
						<td>${book.value.price}</td>
						<td>${book.value.totalPrice}</td>
						<td><a id="deleteBook_cart_btn" href="cartServlet?action=deleteItem&id=${book.key}">删除</a></td>
					</tr>
				</c:forEach>
				<script type="text/javascript">
					$(function (){
						$("#deleteBook_cart_btn").click(function (){
							return (confirm("是否要从购物车中删除图书《"+$(this).parent().parent().find("td:first").text()+"》?"));

						})
					})
				</script>
			</table>

			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>

		</div>
	</c:if>


	<!--静态包含页脚内容-->
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>