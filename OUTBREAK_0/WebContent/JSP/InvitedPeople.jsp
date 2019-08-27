<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.UserBeanCL" %>
<%@ page import="com.outbreak.util.DBConnect" %>
<%@ page import="java.sql.ResultSet" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>受邀名单</title>
</head>
<body>
<%
	String meetingid=request.getParameter("meetingId");
	int MID=Integer.parseInt(meetingid);
	String meetingName=request.getParameter("meetingName");
	DBConnect db=new DBConnect();
	db.connect();
	ResultSet inviteds=db.searchPeople(MID);
%>
<header><h1><%=meetingName %></h1></header>
<table>
	<tr>
		<th>姓名</th><th>状态</th>
	</tr>
<%
	if(inviteds==null){
		%>
	<tr><td>null</td><td>null</td></tr>
		<%
	}else{
		while(inviteds.next()){
			String uid=inviteds.getString("uid");
			int status=inviteds.getInt("TOF");
			String statusString;
			if(status==0){
				statusString="未确定参加";
			}else{
				statusString="确定参加";
			}
			%>
	<tr><td><%=uid %></td><td><%=statusString %></td></tr>
			<%
		}
	}
%>
</table>
</body>
</html>