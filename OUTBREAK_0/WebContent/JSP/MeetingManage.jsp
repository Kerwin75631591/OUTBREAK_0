<%@ page language="java" contentType="text/html; 
charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>会议管理</title>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/CSS/MeetingManage.css">
<script>
	function jumpToCreate(){
		//alert(" ");
		var email=document.getElementById("email").value;
		window.open("../JSP/MeetingCreate.jsp?email="+email);
		window.close();
	}
</script>
</head>
<body>
	<%
		String sessionName = (String) request.getSession().getAttribute("sessionname");
		String sessionEmail = (String) request.getSession().getAttribute("sessionemail");
	%>

	<div id="MngTopBox">
		<div id="MngWelcome">
			您好，<span id="email"><%=request.getSession().getAttribute("sessionemail")%></span>！
		</div>
		<div id="MngJump">
			<span id="createMeeting"><input type="button"
				id="CreateMeetingBtn" value="发布新会议" onclick="jumpToCreate()"></span>
			<span><input type="button" id="ManageLoginBtn" value="退出登录"
				onclick="window.location.href='Login.jsp';" /></span>
		</div>
	</div>

	<div id="ManageBox">
		<table>
			<tr>
				<th id="name" style="font-size: 20px; width: 300px">会议名称</th>
				<th id="date" style="font-size: 20px; width: 700px">会议日期</th>
				<th id="loc" style="font-size: 20px; width: 300px">会议地点</th>
				<th id="content" style="font-size: 20px; width: 300px">会议内容</th>
				<th id="users" style="font-size: 20px; width: 350px">与会者名单确认</th>
				<th id="status" style="font-size: 20px; width: 300px">状态</th>
				<th></th>
			</tr>
			<%
				MeetingBeanCL mbcl = new MeetingBeanCL();
				ResultSet meetings = mbcl.search(sessionEmail);
				if (meetings == null) {
			%>
			<tr>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
			</tr>
			<%
				} else {
					int counter = 1;
					while (meetings.next()) {
						int meetingid = meetings.getInt("id");
						String name = meetings.getString("name");
						Date startDate = (Date) meetings.getObject("begintime");
						Date endDate = (Date) meetings.getObject("endtime");
						String dateString = startDate + "---" + endDate;
						String loc = meetings.getString("place");
						String topic = meetings.getString("topic");
						int arr = meetings.getInt("ArrivalNum");
						int total = meetings.getInt("peopleNum");
						String numString = arr + "//" + total;
						int state = meetings.getInt("state");
						String stateString = null;
						switch (state) {
						case 0:
							stateString = new String("未提交");
							break;
						case 1:
							stateString = new String("未审核");
							break;
						case 2:
							stateString = new String("审核通过");
							break;
						case 3:
							stateString = new String("审核失败");
							break;
						case 5:
							stateString = new String("会议结束");
							break;
						}
			%>
			<tr>
				<td id=<%="name" + counter%>><%=name%></td>
				<td id=<%="date" + counter%>><%=dateString%></td>
				<td id=<%="loc" + counter%>><%=loc%></td>
				<td id=<%="content" + counter%>><%=topic%></td>

				<%
					String s = "InvitedPeople.jsp?meetingName=" + name + "&meetingId=" + meetingid;
							System.out.println("发送"+s);
				%>
				<td id=<%="num" + counter%>><a href=<%=s%> title=
					"点击查看被邀请者的状态" target="_blank"><%=numString%></a></td>
				<td id=<%="state" + counter%>><%=stateString%></td>
				<td><input type="button" value="修改"
					onclick="submit(<%=counter%>)" <%if (state != 0)%> disabled="disabled"></td>
			</tr>
			<%
				counter++;
					}
					mbcl.db.close();
				}
			%>
		</table>
	</div>

</body>
</html>
