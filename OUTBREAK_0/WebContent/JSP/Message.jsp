<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.outbreak.dao.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
</head>
<body>
	 <%
   		 String message = (String)request.getSession().getAttribute("message");
		 String next = (String)request.getSession().getAttribute("next");
     %>
	<script type="text/javascript">
		alert(<%=message%>);
	</script>
	<%
		response.sendRedirect(next);
     %>
</body>
</html>