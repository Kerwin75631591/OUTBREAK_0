<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<% String path = request.getContextPath();  %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<script type="text/javascript" src="encode.js"></script>
<script>
	var CHECK;
	function subForm(){
		var pw=document.getElementById("pw").value;
		var rpw=document.getElementById("rpw").value;
		var email=document.getElementById("em").value;
		var check=document.getElementById("chk").value;	
		if(email.length==0){
			alert("请输入邮箱");
		}else{
			if(check.length==0){
				alert("请输入验证码");
			}else{
				if(CHECK!=check){
					alert("验证码错误！");
				}else{					
					if(pw==rpw){
						//alert("提交表单");
						var encPw=encode(pw);
						document.getElementById("pw").value=encPw;
						document.forms[0].submit();
					}else{
						alert("两次密码输入不一致！");
					}					
				}
			}
		}
	}
	function postEmail(){
		while(true){
			CHECK=Math.floor(Math.random()*1000000);
			if(CHECK>99999)
				break;
		}
		var email=document.getElementById("em").value;
		//document.getElementById("show_check").innerHTML=CHECK;
		document.getElementById("email_button").disabled=true;
		var e=encode(CHECK);
		//alert(e);
		window.open("PwResetCheckCL.jsp?Email="+email+"&check="+e);
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/PwReset.css">
<title>重置密码</title>
</head>

<body>
   <canvas ></canvas>
   <form id="resetForm" action="PwResetCL.jsp" method="POST">
   <div id="PsRTitle">OUTBREAK 云会议管理系统</div>
   <div id="ResetBox">
      <div>
		 <span><input type="email" id="em" style="border:1px solid #999999;" placeholder="请输入邮箱" name="Email"></span>
		 <span id="email_poster"><input id="email_button" type="button" value="发送验证码" onclick="postEmail()"></span>
      </div>
	  <div><input id="pw" type="password" style="border:1px solid #999999;" placeholder="请输入密码" name="Password"></div>
	  <div><input id="rpw" type="password" style="border:1px solid #999999;" placeholder="请再次输入密码" name="RePassword"></div>
	  <div><input id="chk" type="text" style="border:1px solid #999999;" placeholder="请输入验证码" name="Check"></div>
	  <a id="ResetBack" href="Login.jsp">返回</a>
      <input id="ResetBtn" type="button" class="sendbutton" value="重 置" onclick="subForm()">
   </div>
   </form>
</body>

</html>
