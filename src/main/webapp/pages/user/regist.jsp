<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会员注册页面</title>
		<!--静态包含base标签，css样式，jQuery文件-->
		<%@include file="/pages/common/head.jsp"%>
		
		<script type="text/javascript">
			/*
			 	验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				验证确认密码：和密码相同
				邮箱验证：xxxxx@xxx.com
				验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成
			 */
			$(function(){

				//使用Ajax使页面来检查用户注册的用户名是否可用：用户一离开鼠标 就检查
				$("#username").blur(function (){
					//获取username
					var username=this.value;
					$.getJSON("http://localhost:8080/Book01/userServlet","action=ajaxExistsUsername&username="+username,function (data){
						if(data.existsUsername){
							$(".errorMsg").text("用户名已存在，请重新输入")
						}else{
							$(".errorMsg").text("用户名可用")
						}
					});
				});



				$("#sub_btn").click(function (){
					//验证用户名
					var username=$("#username").val();
					var userRegExp=/^\w{5,12}$/;
					if(!userRegExp.test(username)){
						//用户名输入错误则提示用户
						//span[class='errorMsg']---也可替换 span.errorMsg
						$("span[class='errorMsg']").text("用户名必须由字母，数字下划线组成，并且长度为 5 到 12 位")
						return false;
					}
					//验证密码
					var password=$("#password").val();
					var	pwdRegExp=/^\w{5,12}$/;
					if(!pwdRegExp.test(password)){
						$("span[class='errorMsg']").text("密码必须由字母，数字下划线组成，并且长度为 5 到 12 位");
						return false;
					}
					//验证确认密码
					var rePwd=$("#repwd").val();
					if(password!=rePwd){
						$("span[class='errorMsg']").text("两次密码输入不一致！请重新输入")
						return false;
					}
					//邮箱验证
					var email=$("#email").val()
					var emailRegExp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					if(!emailRegExp.test(email)){
						$("span[class='errorMsg']").text("邮箱验证错误");
						return false;
					}
					//验证码验证
					var codeText=$("#code").val();
					if(codeText==null||codeText==""){
						$("span[class='errorMsg']").text("验证码不能输入为空")
						return  false;
					}
				})
				//给验证码绑定单击事件来切换验证码
				$("#code_img").click(function (){
					//添加附属参数new Date()作用只是为了让请求验证码的地址每次请求地址不一样
					//防止浏览器检查到请求验证码地址一样，于是将验证码图片从缓存中找到原来的图片给填补上，造成刷新验证码不成功的问题
					this.src="kaptchaServlet.jpg?d="+new Date();
				})

			})
		</script>


	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册雷疯书城会员</h1>
								<!--向用户输入错误信息-->
								<span class="errorMsg">

									${empty requestScope.msg?"请输入注册信息":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post"><!--http://localhost:8080-->
								<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<!--
										placeholder用来帮助提示用户输入 显示在文本框内的字体
										autocomplete：属性简单来说提示用户近期输入过的内容，可点击提示的内容来对文本框赋值
													  off是关闭，on是开启
									-->
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email" value="${empty requestScope.email?"":requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
									<img alt="" id="code_img" src="kaptchaServlet.jpg" style="width: 90px;height: 40px; float: right; margin-right: 40px" >
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>

		<!--静态包含页脚内容-->
		<%@include file="/pages/common/footer.jsp"%>

	</body>
</html>