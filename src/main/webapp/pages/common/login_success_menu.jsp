<%--
  Created by IntelliJ IDEA.
  User: 35414
  Date: 2022/3/24
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="orderServlet?action=queryMyOrders">我的订单</a>
        <a href="userServlet?action=logout" id="logout_btn">注销</a>
        <a href="index.jsp">返回</a>
    </div>
    <script type="text/javascript">
        $(function (){
            $("#logout_btn").click(function (){
                return confirm("是否要退出登录?");

            })
        })
    </script>

