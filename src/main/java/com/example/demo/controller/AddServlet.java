package com.example.demo.controller;

import com.example.demo.converter.AuthorConverter;
import com.example.demo.converter.Converter;
import com.example.demo.converter.PostConverter;
import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.BaseDao;

import java.util.Calendar;
import java.util.Date;

import com.example.demo.dao.PostDao;
import com.example.demo.domain.Author;
import com.example.demo.domain.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseDao<Author> authorDao = new AuthorDao(new AuthorConverter());
        List<Author> authors = authorDao.getAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/view/add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String content = req.getParameter("content");
        Date currentDate = Calendar.getInstance().getTime();
        Post post = new Post(title, description, content, currentDate, author);
        BaseDao<Post> postBaseDao = new PostDao(new PostConverter());
        if (postBaseDao.add(post)) {
            resp.sendRedirect(req.getContextPath() + "/index?message=Post created successfully");
        } else {
            resp.sendRedirect(req.getContextPath() + "/index?message=Post created false");
        }
    }
}
