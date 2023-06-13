package com.example.demo.controller;

import com.example.demo.converter.PostConverter;
import com.example.demo.dao.BaseDao;
import com.example.demo.dao.PostDao;
import com.example.demo.domain.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class DeleteServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            BaseDao<Post> postDao = new PostDao(new PostConverter());
            boolean deleted = postDao.delete(id);
            if (deleted) {
                resp.sendRedirect(req.getContextPath() + "/index?message=Post deleted successfully");
            } else {
                resp.sendRedirect(req.getContextPath() + "/index?message=Post deleted false");
            }
        }
    }
}
