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
<title>�������</title>
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
			���ã�<span id="email"><%=request.getSession().getAttribute("sessionemail")%></span>��
		</div>
		<div id="MngJump">
			<span id="createMeeting"><input type="button"
				id="CreateMeetingBtn" value="�����»���" onclick="jumpToCreate()"></span>
			<span><input type="button" id="ManageLoginBtn" value="�˳���¼"
				onclick="window.location.href='Login.jsp';" /></span>
		</div>
	</div>

	<div id="ManageBox">
		<table>
			<tr>
				<th id="name" style="font-size: 20px; width: 300px">��������</th>
				<th id="date" style="font-size: 20px; width: 700px">��������</th>
				<th id="loc" style="font-size: 20px; width: 300px">����ص�</th>
				<th id="content" style="font-size: 20px; width: 300px">��������</th>
				<th id="users" style="font-size: 20px; width: 350px">���������ȷ��</th>
				<th id="status" style="font-size: 20px; width: 300px">״̬</th>
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
							stateString = new String("δ�ύ");
							break;
						case 1:
							stateString = new String("δ���");
							break;
						case 2:
							stateString = new String("���ͨ��");
							break;
						case 3:
							stateString = new String("���ʧ��");
							break;
						case 5:
							stateString = new String("�������");
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
							System.out.println("����"+s);
				%>
				<td id=<%="num" + counter%>><a href=<%=s%> title=
					"����鿴�������ߵ�״̬" target="_blank"><%=numString%></a></td>
				<td id=<%="state" + counter%>><%=stateString%></td>
				<td><input type="button" value="�޸�"
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
