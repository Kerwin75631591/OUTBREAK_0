<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<% String path = request.getContextPath();  %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<script>
	var CHECK;
	function subForm(){
		var pw=document.getElementById("pw").value;
		var rpw=document.getElementById("rpw").value;
		var email=document.getElementById("em").value;
		var check=document.getElementById("chk").value;	
		if(email.length==0){
			alert("����������");
		}else{
			if(check.length==0){
				alert("��������֤��");
			}else{
				if(CHECK!=check){
					alert("��֤�����");
				}else{					
					if(pw==rpw){
						//alert("�ύ��");
						document.forms[0].submit();
					}else{
						alert("�����������벻һ�£�");
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
		var e=encrypt();
		//alert(e);
		window.open("PwResetCheckCL.jsp?Email="+email+"&check="+e);
	}
	function encrypt(){
		//alert(typeof CHECK);
		var enc=CHECK;
		return enc;
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/PwReset.css">
<title>ע�����</title>
</head>

<body>
   <canvas ></canvas>
   <form id="resetForm" action="PwResetCL.jsp" method="POST">
   <div id="ResetBox">
      <div>
	     <span id="ResetIDLabel">�ʺţ�</span>
		 <span><input type="email" id="em" name="Email"></span>
		 <span id="email_poster"><input id="email_button" type="button" value="������֤��" onclick="postEmail()"></span>
      </div>
      <div>
	     <span id="ResetPasswordLabel">���룺</span>
		 <span><input id="pw" type="password" name="Password"></span>
	  </div>
	  <div>
	     <span id="ResetPasswordAgaLabel">�ظ����룺</span>
		 <span><input id="rpw" type="password" name="RePassword"></span>
	  </div>
	  <div>
	     <span id="ResetVerifyCodeLabel">��֤�룺</span>
		 <span><input id="chk" type="text" name="Check"></span>
	  </div>
	  <span><a id="ResetBack" href="Login.jsp">����</a></span>
	  <span><input id="ResetBtn" type="button" value="ע��" onclick="subForm()"></span>
	  
   </div>
   </form>
</body>

</html>
