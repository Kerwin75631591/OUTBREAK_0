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
	function postEmail(){
		while(true){
			CHECK=Math.floor(Math.random()*1000000);
			if(CHECK>99999)
				break;
		}
		var email=document.getElementById("em").value;
		document.getElementById("show_check").innerHTML=CHECK;
		document.getElementById("em").disabled=true;
		document.getElementById("email_button").disabled=true;
		var e=encrypt();
		//alert(e);
		//window.open("CheckCL.jsp?Email="+email+"&check="+e);
	}
	function encrypt(){
		//alert(typeof CHECK);
		var enc="";
		var holder=CHECK;
		var temp=0;
		while(holder!=0){
			temp=holder%100;
			holder=Math.floor(holder/100);
			enc+=String.fromCharCode(temp);
		}
		return enc;
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/Register.css">
<title>ע�����</title>
</head>

<body>
   <canvas ></canvas>
   <form id="regForm" action="RegisterCL.jsp" method="POST">
   <div id="RegistBox">
      <div>
	     <span id="RegistIDLabel">�ʺţ�</span>
		 <span><input type="email" id="em" name="Email"></span>
		 <span id="email_poster"><input id="email_button" type="button" value="������֤��" onclick="postEmail()"></span>
      </div>
      <div>
	     <span id="RegistPasswordLabel">���룺</span>
		 <span><input id="pw" type="password" name="Password"></span>
	  </div>
	  <div>
	     <span id="RegistPasswordAgaLabel">�ظ����룺</span>
		 <span><input id="rpw" type="password" name="RePassword"></span>
	  </div>
	  <div>
	     <span id="RegistVerifyCodeLabel">��֤�룺</span>
		 <span><input id="chk" type="text" name="Check"></span>
	  </div>
	  <span><a id="RegistBack" href="Login.jsp">����</a></span>
	  <span><input id="RegistBtn" type="button" value="ע��" onclick="subForm()"></span>
	  
   </div>
   <p id="show_check">show check!</p>
   </form>
</body>

</html>
