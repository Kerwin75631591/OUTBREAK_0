<%@ page language="java" contentType="text/html; 
charset=GBK" pageEncoding="GBK"%>

<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);

System.out.println(basePath);%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="GBK">
		<title>登录页面</title>
		<script>
			function checkL() {
				if(document.getElementById("VericodeInput").length == 0 || document.getElementById("VericodeInput").value != "7364"){
					alert("验证码错误！");
					return;
				}
				var em = document.getElementById("IDInput").value;
				var pw = document.getElementById("PasswordInput").value;
				if(em.length == 0) {
					alert("请输入账号！");
				} else if(pw.length == 0) {
					alert("请输入密码！");
				} else {
					document.forms[0].submit();
				}
			}
		</script>
		<link rel="stylesheet" href="<%=path %>/CSS/Login.css" >
	</head>
	<body>
		<canvas ></canvas>
		<form action="<%=path %>/JSP/LoginCL.jsp" method="post">
		<div id="LoginBox">
			<div>
				<span id="LoginIDLabel">帐号：</span>
				<span><input type="email" id="IDInput" name="LoginEmail"></span>
            </div>
			<div>
				<span id="LoginPasswordLabel">密码：</span>
				<span><input type="password" id="PasswordInput" name="LoginPassword"></span>
			</div>
			<div>
				<span id="LoginVericodeLabel">验证码：</span>
				<span><input type="text" id="VericodeInput" name="LoginVericode"></span>
				<span id="LoginVericodePic"></span>
			</div>
			<span><input type="button" id="RegistWant" value="注册" onclick="window.location.href='<%=path %>/JSP/Register.jsp'"></span>
			<span><input type="button" id="LoginInput" value="登录" onclick="checkL()"></span>
		</div>
		</form>
	</body>
</html>       