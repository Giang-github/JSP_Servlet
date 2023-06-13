package com.example.demo.dao.query;

public class Query {
    public static final String GET_ALL_POST ="SELECT * FROM posts";
    public static final String GET_POST_BY_ID ="SELECT * FROM posts WHERE id = ?";
    public static final String GET_ALL_AUTHORS = "SELECT * FROM authors";
    public static final String INSERT_POST = "INSERT INTO posts (author, title, description, content, date) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_POST = "DELETE FROM posts WHERE id = ?";
    public static final String EDIT_POST = "UPDATE posts SET author=?, title=?, description=?, content=?, date=? WHERE id=?";
    }
