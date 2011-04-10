<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>师生交流平台</title>
<link href="/resources/themes/css/login.css" rel="stylesheet" type="text/css" />
<script src="/resources/js/jquery-1.4.min.js" type="text/javascript"></script>
</head>
<body>
<div id="login">
	<div id="login_header">
		<h1 class="login_logo">

			<a href="#"><img src="/resources/themes/default/images/login_logo.gif" /></a>
		</h1>
		<div class="login_headerContent">
			<div class="navList">
				<ul>
					<li><a href="#">设为首页</a></li>
					<li><a href="#">升级说明</a></li>
					<li><a href="#">反馈</a></li>

					<li><a href="#">帮助</a></li>
				</ul>
			</div>
			<h2 class="login_title"><img src="/resources/themes/default/images/login_title.png" /></h2>
		</div>
	</div>
	<div id="login_content">
		<div class="loginForm">

			<form method="post" action="j_spring_security_check">
				<p>
					<label>帐号：</label>
					<input type="text" name="j_username" size="20" class="login_input" />
				</p>
				<p>
					<label>密码：</label>
					<input type="password" name="j_password" size="20" class="login_input" />

				</p>
				<p>
					<label style="width:130px">两周内不用再登录：</label>
					<input type="checkbox" name="_spring_security_remember_me"/>
				</p>
				<div class="login_bar">
					<input class="sub" type="submit" value=" " />

				</div>
           </form>
		</div>
		<div class="login_banner"><img src="/resources/themes/default/images/login_banner.jpg" /></div>
		<div class="login_main">
			<!--<ul class="helpList">
				<li><a href="#">下载驱动程序</a></li>
				<li><a href="#">如何安装密钥驱动程序？</a></li>
				<li><a href="#">忘记密码怎么办？</a></li>
				<li><a href="#">为什么登录失败？</a></li>
			</ul>-->
			<div class="login_inner">
				<p>测试账号(用户名/密码)</p>

				<p>管理员：admin/admin</p>
				<p>领导：leader/leader</p>
				<p>员工：member/member</p>
				<p>演示：demo/demo</p>
			</div>
		</div>
	</div>

	<div id="login_footer">
		Copyright &copy; 20011 nju.software Inc. All Rights Reserved.
	</div>
</div>

</body>
</html>