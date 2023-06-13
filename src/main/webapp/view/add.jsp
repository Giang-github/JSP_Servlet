<%@page import="com.example.demo.common.BaseConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.domain.Author"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Post</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Add Post</h2>
    <form method="post" action="/addpost">
        <div class="form-group">
            <label for="author">Author:</label>
            <select name="author" id="author" class="form-control">
                <option value="">-- Select author --</option>
                <% for (Author author : (List<Author>)request.getAttribute("authors")) { %>
                    <option value="<%= author.getUsername() %>">
                        <%= author.getFirstName() %> <%= author.getLastName() %>
                    </option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea name="description" id="description" rows="3" class="form-control"></textarea>
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea name="content" id="content" rows="10" class="form-control"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Post</button>
    </form>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>

