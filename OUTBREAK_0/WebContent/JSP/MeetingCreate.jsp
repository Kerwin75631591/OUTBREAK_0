<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
	<%@ page import="com.outbreak.dao.*"%>
	<%@ page import="com.outbreak.util.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<% 
	String path = request.getContextPath(); 

	String meetingid = request.getParameter("meetingid");
	String meetingName = "";
	String meetingTopic = "";
	String meetingPlace = "";
	int BeginH = 00;
	int BeginM = 00;
	int EndH = 00;
	int EndM = 00;
	String meetingData = "";
	String begintime = "";
	String endtime ="";
	String meetingContent = "";
	String Users = "";
	
	if(!(meetingid == null || "".equals(meetingid.trim())))
	{
		int mid = Integer.parseInt(meetingid);
		
		DBConnect db = new DBConnect();
		db.connect();
		ResultSet meeting = db.searchMeeting(mid);
		
		
		if(meeting != null)
		{
			meeting.next();
			meetingName = meeting.getString("name");
		    meetingTopic = meeting.getString("topic");
		    meetingPlace = meeting.getString("place");
		    meetingContent = meeting.getString("content");
		    begintime = meeting.getString("begintime");
			endtime = meeting.getString("endtime");
		    Date meetingBegintime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begintime);
			Date meetingEndtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime);
			meetingData = begintime.substring(0, 10);
			BeginH = meetingBegintime.getHours();
			BeginM = meetingBegintime.getMinutes();
			EndH = meetingEndtime.getHours();
			EndM = meetingEndtime.getMinutes();
		}
		
		ResultSet people = db.searchPeople(mid);
		while(people.next())
		{
			Users += (people.getString("Uid") + "-" + people.getString("PhoneNum") + "-" + people.getString("Email") + "-");
		}
		System.out.println(Users);
		
		db.close();
	}
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="GBK">
<title>创建会议</title>
<link rel="stylesheet" href="../CSS/MeetingCreate.css" />
<script type="text/javascript">
	function Release(){
		
		
        var meetingName = document.getElementById("meetingName").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingTopic = document.getElementById("meetingTopic").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingData = document.getElementById("meetingData").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingPlace = document.getElementById("meetingPlace").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingContent = document.getElementById("meetingContent").value.replace(/(^\s*)|(\s*$)/g, '');
		var rowNum = document.getElementById("UserTable").rows.length;
		
		//判断是否填满每一项（文件除外）
		if(meetingName == '' || meetingTopic == '' || meetingPlace == '' || meetingContent == '' || meetingData == '' || rowNum == 1){
			alert("请完整填写申请表后再提交，或者先保存为草稿！");
			return;
		}
		
		//时间格式判断
		var meetingBeginH = document.getElementById("BeginH").value;
		var meetingBeginM = document.getElementById("BeginM").value;
		var meetingEndH = document.getElementById("EndH").value;
		var meetingEndM = document.getElementById("EndM").value;
		if (isNaN(meetingBeginH) || meetingBeginH < 0 || meetingBeginH > 24 ||
			isNaN(meetingBeginM) || meetingBeginM < 0 || meetingBeginM > 59 ||	
			isNaN(meetingEndH) || meetingEndH < 0 || meetingEndH > 24 ||
			isNaN(meetingEndM) || meetingEndM < 0 || meetingEndM > 59 || meetingBeginH > meetingEndH) {
		    alert("这不是一个有效的时间段");
		    return;
		}else{
			if(meetingBeginH == meetingEndH){
				if(meetingBeginM > meetingEndM){
					alert("这不是一个有效的时间段");
				    return;
				}
			}
		}
		
		//时间格式转化为"HH:mm:ss"
		meetingBegintime.value = meetingBeginH + ":" + meetingBeginM + ":00";
		meetingEndtime.value = meetingEndH + ":" + meetingEndM + ":00";
		
		if(!(document.getElementById("uploadFile").value == "")){
			alert("Release with file!");
			document.meetingManageForm.enctype = "multipart/form-data" 
			document.meetingManageForm.action = "/OUTBREAK_0/ReleaseServlet";
	        document.meetingManageForm.submit();
		}else{
			alert("Release without file!");
			document.meetingManageForm.action = "/OUTBREAK_0/ReleaseWithoutFileServlet";
	        document.meetingManageForm.submit();
		}
	}
	function Save(){
		if(!(document.getElementById("uploadFile").value == "")){
			alert("保存草稿功能不支持保存文件，请在发布时再上传文件！");
		}
		
		var meetingBeginH = document.getElementById("BeginH").value;
		var meetingBeginM = document.getElementById("BeginM").value;
		var meetingEndH = document.getElementById("EndH").value;
		var meetingEndM = document.getElementById("EndM").value;
		
		if(!(meetingBeginH == "" || meetingBeginM == "" || meetingEndH == "" || meetingEndM == "" || document.getElementById("meetingData").value == "")){
			//时间格式判断
			if (isNaN(meetingBeginH) || meetingBeginH < 0 || meetingBeginH > 24 ||
				isNaN(meetingBeginM) || meetingBeginM < 0 || meetingBeginM > 60 ||	
				isNaN(meetingEndH) || meetingEndH < 0 || meetingEndH > 24 ||
				isNaN(meetingEndM) || meetingEndM < 0 || meetingEndM > 60 || meetingBeginH > meetingEndH) {
			    alert("这不是一个有效的时间段");
			    return;
			}else{
				if(meetingBeginH == meetingEndH){
					if(meetingBeginM > meetingEndM){
						alert("这不是一个有效的时间段");
					    return;
					}
				}
			}
			//时间格式转化为"hh:mm"
			document.getElementById("meetingBegintime").value = document.getElementById("BeginH").value + ":" + document.getElementById("BeginM").value;
			document.getElementById("meetingEndtime").value = document.getElementById("EndH").value + ":" + document.getElementById("EndM").value;
		}else{
			document.getElementById("meetingBegintime").value = "";
			document.getElementById("meetingEndtime").value = "";
		}
		
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
		
		var rowIndex = obj.parentNode.parentNode.rowIndex;
		var row = document.getElementById("UserTable").rows[rowIndex];
		var move = row.cells[1] + "-" + row.cells[2] + "-" + row.cells[3] + "-";
		//删除Users里面的指定内容
		var temp = document.getElementById("Users").value;
		temp.replace(move, "");
        Users.value = temp;
		
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
	<div id="CrtTopBox">
	   <div id="CreateWelcome">您好，<%=request.getSession().getAttribute("sessionemail") %>！</div>
	   <div id="CreateJump">
	      <span><input type="button" id="CreateMeetingManageBtn" 
	            value="管理会议" onclick="window.location.href='MeetingManage.jsp';"/></span>
	      <span><input type="button" id="CreateLoginBtn" 
	            value="退出登录" onclick="window.location.href='Login.jsp';"/></span>
	   </div>
	</div>
	
	<form action="" id="meetingManageForm" name="meetingManageForm" method="post">

	
	<div id="CrtLeftBox">
	   <table width="100%" border="0" cellspacing="10" style="margin-top: 10px; margin-left: 20px">
		  <tr>
		     <td style="font-size: 20px">会议名称：</td>
		     <td><input type="text" id="meetingName" name="meetingName" value="<%=meetingName%>"
		         style="width: 500px; height: 30px; font-size: 30px;"></td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">会议主题：</td>
			 <td><input id="meetingTopic" name="meetingTopic" type="text"  value="<%=meetingTopic%>"
			 style="width: 500px; height: 30px; font-size: 30px"></td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">会议日期：</td>
			 <td><input id="meetingData" name="meetingData" type="date" value="<%=meetingData%>"
			 style="width: 500px; height: 30px; font-size: 30px"></td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">会议时间：</td>
			 <td>
			    <table id="TimeTable">
			       <tr>
			          <td style="font-size: 20px;"><input id="BeginH" type="text" value="<%=BeginH%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			          <td style="font:bold;font-size: 20px; width: 50px;text-align:center;">:</td>
			          <td style="font-size: 20px;"><input id="BeginM" type="text" value="<%=BeginM%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			          <td style="font:bold;font-size: 20px; width: 80px;text-align:center;">至</td>
			          <td style="font-size: 20px;"><input id="EndH" type="text" value="<%=EndH%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			          <td style="font:bold;font-size: 20px; width: 50px;text-align:center;">:</td>
			          <td style="font-size: 20px;"><input id="EndM" type="text" value="<%=EndM%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			       </tr>
			    </table>
			 </td>
		  </tr>
		  <tr>
			 <td style="font-size: 20px">会议地点：</td>
			 <td><input id="meetingPlace" name="meetingPlace" type="text" value="<%=meetingPlace%>"
			 style="width: 500px; height: 30px; font-size: 30px"></td>
		  </tr>
		  <tr>
			 <td style="font-size: 20px">会议内容：</td>
			 <td><textarea id="meetingContent" name="meetingContent" style="line-height: 30px; width: 500px; height: 350px; font-size: 30px"><%=meetingContent%></textarea></td>
		  </tr>
		  <tr>
			 <td style="font-size: 20px">上传资料：</td>
			 <td><input id="uploadFile" name="uploadFile" type="file" style="width: 500px; height: 30px; font-size: 20px"></td>
		  </tr>
	   </table>
	</div>
	<div id="CrtRightBox">
	<div id="Box">
	   <table id="UserTable" name="UserTable">
	      <tr>
		     <td style="font-size: 20px; margin-left: -400px; width:100px;">与会人员：</td>
			 <td style="font-size: 20px; width:250px; text-align:center;">人员姓名</td>
			 <td style="font-size: 20px; width:250px; text-align:center;">联系方式</td>
			 <td style="font-size: 20px; width:250px; text-align:center;">邮箱地址</td>
			 <td style="font-size: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  </tr>
		  <%
		  	String[] guests = Users.split("-");
		  	for(int i = 0; i<guests.length-2; i += 3)
			{
		  %>
		  	<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><%=guests[i]%></td>
				<td><%=guests[i+1]%></td>
				<td><%=guests[i+2]%></td>
				<td><input type="button" value="删除" onclick="Delete(this)"></td>
			</tr>
		  <%
			}
		  %>
	   </table>
	</div>
	   <table id="InputTable">
	      <tr>
		     <td style="font-size: 20px; text-align:center;">输入信息：</td>
			 <td style="font-size: 20px; text-align:center;">人员姓名</td>
			 <td style="font-size: 20px; text-align:center;">联系方式</td>
			 <td style="font-size: 20px; text-align:center;">邮箱地址</td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">&nbsp;</td>
			 <td><input type="text" id="Name" style="height: 20px; font-size: 20px"></td>
			 <td><input type="text" id="Phone" style="height: 20px; font-size: 20px"></td>
			 <td><input type="email" id="Email" style="height: 20px; font-size: 20px"></td>
			 <td><span><input type="button" id="CreateAdd" value="增  加" onclick="Add()"></span></td>
		  </tr>
	   </table>
	</div>
	<div id="CrtBottomBox">
	   <input type="button" id="CreateRelease" value="发  布" onclick="Release()"> 
	   <input type="button" id="CreateSave" value="保存草稿" onclick="Save()"> 
	   <input type="reset" id="CreateReset" value="重  置" onclick="Reset()">
	   <input type="text" id="Users" name="Users" style="display:none" value="<%=Users%>">
	   <input type="text" id="meetingBegintime" name="meetingBegintime" style="display:none" value="<%=begintime%>">
  	   <input type="text" id="meetingEndtime" name="meetingEndtime" style="display:none" value="<%=endtime%>">

	</div>
	</form>
<<<<<<< HEAD

</body>
=======
</body>
<script>
	window.onload = function(){
		<% 
			String email = request.getParameter("email");
			if(email == null || "".equals(email.trim()))
			{
				return;
			}
			
			MeetingBeanCL mbcl = new MeetingBeanCL();
			ResultSet meetings = mbcl.search(email);
			
			if(meetings == null)
			{
				return;
			}
		%>
			
	}
</script>
>>>>>>> 69d91b7ec6ab008c3a3cc9ae1ab6825296fe4f09
</html>