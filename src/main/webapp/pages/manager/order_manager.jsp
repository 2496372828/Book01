<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<!--静态包含base标签，css样式，jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>

</head>
	<script type="text/javascript">
		$(function (){
			$(".sendOrder").click(function (){
				//点击发货按钮后，弹出选项框来确认
				var orderId=$(this).parent().parent().find("td:first").text();

				return confirm("订单"+orderId+"确定要发货吗");
			})
		})
	</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<!--静态包含图书管理菜单-->
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户id</td>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>商品详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.userId}</td>
					<td>${order.orderId}</td>
					<td>${order.createDate}</td>
					<td>${order.price}</td>
					<td><a href="orderServlet?action=showOrderDetails&orderId=${order.orderId}">查看商品详情</a></td>
					<%--
						此处应有的功能为：根据订单状态来判断，订单未发货显示"点击发货" 然后点击发货后，此处应该回显已发货

						根据订单状态--0	点击发货-->orderServlet -sendOrder
								   --1  已发货
								   --2  已签收
					--%>
					<c:if test="${order.status==0}">
						<td><a href="orderServlet?action=sendOrder&orderId=${order.orderId}" class="sendOrder">点击发货</a></td>
					</c:if>
					<c:if test="${order.status==1}">
						<td>已发货</td>
					</c:if>
					<c:if test="${order.status==2}">
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