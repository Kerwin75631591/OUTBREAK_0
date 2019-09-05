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
        <div id="LoginSTitle">OUTBREAK</div>
        <div id="LoginXTitle">云会议管理系统</div>
		<div id="LoginBox">
		   <span><input type="email" id="IDInput"  style="border:1px solid #999999;" placeholder="请输入邮箱" name="LoginEmail"></span>
		   <span><input type="password" id="PasswordInput" style="border:1px solid #999999;" placeholder="请输入密码" name="LoginPassword"></span>
			 <div>
				<span><input type="text" id="VericodeInput" style="border:1px solid #999999;" placeholder="请输入验证码" name="LoginVericode"></span>
				<span id="LoginVericodePic"></span>
			 </div>
			<input type="button" id="LoginInput" class="logbutton ripple" data-ripple-color="white" value="登  录"  onclick="checkL()">
			<text id="JumpText" style="color:#808080">还没有账号？请</text><a id="RegistText" href="./JSP/Register.jsp">注册</a>
			<text id="JumpToPwReset" style="color:#808080">忘记密码？<a href="PwReset.jsp">点击这里！</a></text>
		</div>
		</form>
	</body>
</html>       