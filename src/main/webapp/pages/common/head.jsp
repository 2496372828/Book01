<%--
  Created by IntelliJ IDEA.
  User: 35414
  Date: 2022/3/24
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--一般情况下base标签下路径写到工程路径下写base标签，永远固定相对路径跳转的结果-->

<%
        String basePath = request.getScheme()//协议
                + "://"
                + request.getServerName()//客户端访问ip
                + ":"
                + request.getServerPort()//端口号
                + request.getContextPath()//工程路径
                + "/";
        pageContext.setAttribute("basePath",basePath);
%>
<!--写 base 标签，永远固定相对路径跳转的结果,我们获取的都是动态-->
<base href=<%=basePath%>>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
