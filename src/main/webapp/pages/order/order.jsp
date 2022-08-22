<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<!--静态包含base标签，css样式，jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

		<!--静态包含，登录之后的菜单-->
		<%@include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>商品详情</td>
				<td>签收状态</td>
			</tr>

		<c:forEach items="${sessionScope.ordersByUserId}" var="order">
			<tr>
				<td>${order.createDate}</td>
				<td>${order.price}</td>
				<td><a href="orderServlet?action=showOrderDetails&orderId=${order.orderId}">查看详情</a></td>
				<c:if test="${order.status==0}"><%--未发货不可签收--%>
					<td>未发货</td>
					<td>订单未发货</td>
				</c:if>
				<c:if test="${order.status==1}"><%--已发货可以签收--%>
					<td>已发货</td>
					<td><a href="orderServlet?action=receiveOrder&orderId=${order.orderId}">订单签收</a></td>
				</c:if>
				<c:if test="${order.status==2}"><%--已签收--%>
					<td>已签收</td>
					<td>已签收</td>
				</c:if>


			</tr>
		</c:forEach>

			

		</table>
		
	
	</div>


	<!--静态包含页脚内容-->
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>