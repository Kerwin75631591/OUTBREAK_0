<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会议创建页面</title>
<link rel="stylesheet" href="../CSS/MeetingCreate.css" />
<script type="text/javascript">
	function Release(){
		document.meetingManageForm.action = "/OUTBREAK_0/ReleaseServlet";
		document.meetingManageForm.submit();
	} 
	function Save(){
        document.meetingManageForm.action = "/OUTBREAK_0/SaveServlet";
        document.meetingManageForm.submit();
 　　}
	function Add(){
        var Name = document.getElementById("Name").value;
        var Phone = document.getElementById("Phone").value;
        var Email = document.getElementById("Email").value;
        
        //E-mail验证
        var atpos = Email.indexOf("@");
        var dotpos = Email.lastIndexOf(".");
        if (atpos<1 || dotpos<atpos+2 || dotpos+2>=Email.length){
          alert("不是一个有效的 e-mail 地址");
          return false;
        }
        
        //联系方式验证
        if (isNaN(Phone)){
        	alert("不是一个有效的联系方式");
            return false;
        }
        
        //添加到Users里面，故可以传输至servlet
		var temp = document.getElementById("Users").value;
        Users.value = temp + Name + "-" + Phone + "-" + Email + "-";

        //添加到UsersTable中
		var trObj = document.createElement("tr");
		trObj.id = new Date().getTime();
		trObj.innerHTML = "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"+Name+"</td><td>"+Phone+"</td><td>"+Email+"</td><td><input type='button' value='删除' onclick='Delete(this)'></td>";
		document.getElementById("UserTable").appendChild(trObj);
		
		//重置输入框
		document.getElementById("Name").value = null;
        document.getElementById("Phone").value = null;
        document.getElementById("Email").value = null;
 　　}
	function Delete(obj){
		var trId = obj.parentNode.parentNode.id;
		var trObj = document.getElementById(trId);
		document.getElementById("UserTable").removeChild(trObj);
	}
	function Reset(){ 
		var tb = document.getElementById("UserTable");
		var rowNum = tb.rows.length;
	    for (var i = 0; i<rowNum; ++i)
	    {
	        tb.deleteRow(1);
	        --rowNum;
	    }
	}
	</script>
</head>
<body>
	<canvas></canvas>
	<div id="CreateToptitle">OUTBREAK 多客户端云会议系统</div>
	<div id="CreateWelcome">
		您好，<%=request.getParameter("email") %>！
	</div>
	<div id="CreateJump">
		<a id="CreateMeetingManageL" href="MeetingManage.jsp">管理会议</a> <span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
		<a id="CreateLoginL" href="Login.jsp">退出登陆</a>
	</div>
	<form action="" id="meetingManageForm" name="meetingManageForm" method="post">
		<table id="CreateTable">
			<td>
				<table width="100%" border="0" cellspacing="5"
					style="margin-top: 40px; margin-left: 20px">
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">会议名称：</td>
						<td><input type="text" id="meetingName" name="meetingName"
							style="width: 500px; height: 30px; font-size: 30px; border: 1px solid #E2E3E5;"></td>
					</tr>
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">会议主题：</td>
						<td><input type="text" id="meetingTopic" name="meetingTopic"
							style="width: 500px; height: 30px; font-size: 30px"></td>
					</tr>
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">日期：</td>
						<td><input type="date" id="meetingData" name="meetingData"
							style="width: 500px; height: 30px; font-size: 30px"></td>
					</tr>
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">会议地点：</td>
						<td><input type="text" id="meetingPlace" name="meetingPlace"
							style="width: 500px; height: 30px; font-size: 30px"></td>
					</tr>
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">会议内容：</td>
						<td><textarea id="meetingContent" name="meetingContent"
								style="line-height: 30px; width: 500px; height: 400px; font-size: 30px"></textarea></td>
					</tr>
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">上传资料：</td>
						<td><input type="file" name="uploadFile"
							style="width: 500px; height: 30px; font-size: 20px"></td>
					</tr>
				</table>
			</td>
			<td>
				<table id="UserTable" name="UserTable" style="margin-top: 0px;">
					<tr>
						<td
							style="border: 1px solid #E2E3E5; font-size: 20px; margin-left: -400px">与会人员：</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">人员姓名</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">联系方式</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">邮箱地址</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</table>

				<table id="InputTable">
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">输入信息：</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">人员姓名</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">联系方式</td>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">邮箱地址</td>
					</tr>
					<tr>
						<td style="border: 1px solid #E2E3E5; font-size: 20px">&nbsp;</td>
						<td><input type="text" id="Name"
							style="height: 20px; font-size: 20px"></td>
						<td><input type="text" id="Phone"
							style="height: 20px; font-size: 20px"></td>
						<td><input type="email" id="Email"
							style="height: 20px; font-size: 20px"></td>
						<td><span><input type="button" id="CreateAdd"
								value="增  加" onclick="Add()"></span></td>
					</tr>
				</table>
			</td>
		</table>
		<input type="button" id="CreateRelease" value="发  布" onclick="Release()"> 
		<input type="button" id="CreateSave" value="保存草稿" onclick="Save()"> 
		<input type="reset" id="CreateReset" value="重  置">
		<input type="text" id="Users" name="Users" style="display:none">
		<div id="CreateSubmitBox"></div>
	</form>

</body>
</html>