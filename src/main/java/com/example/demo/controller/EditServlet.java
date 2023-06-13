package com.example.demo.controller;

import com.example.demo.converter.AuthorConverter;
import com.example.demo.converter.PostConverter;
import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.BaseDao;
import com.example.demo.dao.PostDao;
import com.example.demo.domain.Author;
import com.example.demo.domain.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            BaseDao<Post> postDao = new PostDao(new PostConverter());
            Optional<Post> post = postDao.findById(id);
            if (post.isPresent()) {
                req.setAttribute("post", post.get());
            } else {
                req.getRequestDispatcher("/view/404.jsp").forward(req, resp);
                return;
            }
        }

        BaseDao<Author> authorDao = new AuthorDao(new AuthorConverter());
        List<Author> authors = authorDao.getAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/view/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String authorUsername = req.getParameter("author");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String content = req.getParameter("content");
        Date currentDate = Calendar.getInstance().getTime();
        Post post = new Post(title, description, content, currentDate, authorUsername);

        PostDao postDao = new PostDao(new PostConverter());
        if (postDao.edit(id, post)) {
            resp.sendRedirect(req.getContextPath() + "/index?message=Post updated successfully");
        } else {
            resp.sendRedirect(req.getContextPath() + "/index?message=Post updated false");
        }
    }

}

