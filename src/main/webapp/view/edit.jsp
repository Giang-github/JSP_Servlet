<%@page import="com.example.demo.common.BaseConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.domain.Author"%>
<%@ page import="com.example.demo.domain.Post"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Edit Post</h2>
    <form method="post" action="/postedit">
    <%
    		Post post = (Post) request.getAttribute("post");
    		%>
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <div class="form-group">
            <label for="author">Author:</label>
            <select name="author" id="author" class="form-control">
                <option value="">-- Select author --</option>
                <% for (Author author : (List<Author>) request.getAttribute("authors")) { %>
                    <option value="<%= author.getUsername() %>"
                     <%= post.getAuthor() != null && post.getAuthor().equals(author.getUsername()) ? "selected" : "" %> >
                        <%= author.getFirstName() %> <%= author.getLastName() %>
                    </option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" class="form-control" value="<%= post.getTitle() %>"/>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea name="description" id="description" rows="3" class="form-control"><%= post.getDescription() %></textarea>
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea name="content" id="content" rows="10" class="form-control"><%= post.getContent() %></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update Post</button>
    </form>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
