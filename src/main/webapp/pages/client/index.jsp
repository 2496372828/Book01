<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!--静态包含base标签，css样式，jQuery文件-->
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
          /* 给添加到购物车绑定单击事件*/
            $("button.addToCart").click(function (){

                /*var bookId=$(this).attr("bookId");
                location.href="http://localhost:8080/Book01/cartServlet?action=addItem&id="+bookId;*/


                    /**
                     * 在事件响应的 function 函数 中，有一个 this 对象，这个 this 对象，是当前正在响应事件的 dom 对象
                     * @type {jQuery}
                     */
                    var bookId = $(this).attr("bookId");
                // location.href = "http://localhost:8080/book/cartServlet?action=addItem&id=" + bookId;
                // 发 ajax 请求，添加商品到购物车
                    $.getJSON("http://localhost:8080/Book01/cartServlet","action=aJaxAddItem&id="+
                        bookId,function (data){
                        $("#cartTotalCount").text("您的购物车中有 " + data.totalCount + " 件商品");
                        $("#cartLastName").text(data.lastName);
                    })
                });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo003.jpg" >
    <span class="wel_word">网上书城</span>
    <div>
        <%--如果用户未登录--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="orderServlet?action=queryMyOrders">我的订单</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;
            <a href="index.jsp">返回</a>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a>
            <a href="pages/user/regist.jsp">注册</a>

        </c:if>

            <a href="pages/cart/cart.jsp">购物车</a>
            <%--对于后台管理可以进行一个管理员账户的设置可见性--%>
            <c:if test="${'admin'==sessionScope.user.username}">
                <a href="pages/manager/manager.jsp">后台管理</a>
            </c:if>



    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="/Book01/client/bookServlet" method="get">
                <input type="hidden" name="action" value="selectPageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <%--提交按钮点击之后发送到servlet程序中进行价格查询，然后将查询的page对象返回到index.jsp页面中--%>
                <input type="submit" value="查询" />
            </form>


        </div>
        <c:if test="${not empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span id="#cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span id="cartLastName" style="color: #ff0000">${sessionScope.newBookName}</span>加入到了购物车中
                </div>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span>您的购物车中没有商品</span>
                <div>
                    <span style="color: #ff0000">快去购物吧！！</span>
                </div>
            </div>
        </c:if>


        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>



    </div>
    <%--静态包含分页条--%>
    <%@include file="/pages/common/pagination.jsp"%>


</div>

<!--静态包含页脚内容-->
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>