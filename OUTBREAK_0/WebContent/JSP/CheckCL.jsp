<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.outbreak.dao.EmailPoster" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String check=request.getParameter("Check");
	String email=request.getParameter("Email");
	EmailPoster emailPoster=new EmailPoster(email);
	emailPoster.sendRegEmail(Integer.parseInt(check));
%>
</body>
</html>