<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.outbreak.dao.EmailPosterCL" %>
<%@ page import="com.outbreak.entity.Decoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String receiver=request.getParameter("Email");
	String c=request.getParameter("check");
	String check=Decoder.decode(c);
	EmailPosterCL.sendRegCheck(receiver, Integer.parseInt(check));
%>
<script>
	window.close();
</script>
</body>
</html>