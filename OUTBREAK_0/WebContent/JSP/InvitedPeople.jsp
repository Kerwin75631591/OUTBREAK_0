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
<title>��������</title>
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
		<th>����</th><th>״̬</th>
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
				statusString="δȷ���μ�";
			}else{
				statusString="ȷ���μ�";
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