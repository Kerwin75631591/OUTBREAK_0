<%@ page language="java" contentType="text/html; 
charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>会议管理</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/CSS/MeetingManage.css">
<script>
	function jumpToCreate(){
		var email=document.getElementById("email").value;
		window.open("MeetingCreate.jsp?email="+email);
	}
</script>
</head>
<body>
<%
	String sessionName=(String)request.getSession().getAttribute("sessionname");
	String sessionEmail=(String)request.getSession().getAttribute("sessionemail");
%>
	<header>
	<span id="title">OUTBREAK 多客户端云会议系统</span>
	<span id="createMeeting"><input type="button" value="发布新会议" onclick="jumpToCreate()"></span>
	</header>
	<div id="back">
		您好，<span id="email"><%=sessionEmail %></span>！&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="Login.jsp">退出登陆</a>
	</div>
		
	<table>
		<tr>
			<th id="name">会议名称</th>
			<th id="date">会议日期</th>
			<th id="loc">会议地点</th>
			<th id="content">会议内容</th>
			<th id="users">与会者名单确认</th>
			<th id="status">状态</th><th></th>
		</tr>
	<% 
		MeetingBeanCL mbcl=new MeetingBeanCL();
		ResultSet meetings=mbcl.search(sessionEmail);
		if(meetings==null){
	%>
		<tr>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td><input type="button" value="修改"></td>
		</tr>
	<%}else{ 
		int counter=1;
		while(meetings.next()){
			String name=meetings.getString(4);
			Date date=(Date)meetings.getObject(2);
			String loc=meetings.getString(3);
			String cont=meetings.getString(5);
			int arr=meetings.getInt(9);
			int total=meetings.getInt(8);
			String numString=arr+"//"+total;
			int state=meetings.getInt(7);
			String stateString=null;
			switch(state){
			case 0:
				stateString=new String("未提交");
				break;
			case 1:
				stateString=new String("未审核");
				break;
			case 2:
				stateString=new String("审核通过");
				break;
			case 3:
				stateString=new String("审核失败");
				break;
			case 5:
				stateString=new String("会议结束");
				break;
			}
	%>
		<tr>
			<td id=<%="name"+counter %>><%=name %></td>
			<td id=<%="date"+counter %>><%=date %></td>
			<td id=<%="loc"+counter %>><%=loc %></td>
			<td id=<%="content"+counter %>><%=cont %></td>
			<td id=<%="num"+counter %>><a href="InvitedPeople.jsp" title="点击查看被邀请者的状态" target="_blank"><%=numString %></a></td>
			<td id=<%="state"+counter %>><%=stateString %></td>
			<td><input type="button" value="修改" onclick="submit(<%=counter%>)" <%if(state!=0)%> disabled="disabled"></td>
		</tr>
	<%
			counter++;
		}
	} 
	%>
	</table>
	
</body>
</html>