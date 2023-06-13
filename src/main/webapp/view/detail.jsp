<%@page import="com.example.demo.common.BaseConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.domain.Post"%>

<html>
	<body>

		<%
		Post post = (Post) request.getAttribute("post");
		%>

		<h2><%=post.getTitle()%></h2>
		<p><%=post.getContent()%></p>
		<h2><%=post.getAuthor()%></h2>
        <p><%=post.getDate()%></p>

	</body>
</html>