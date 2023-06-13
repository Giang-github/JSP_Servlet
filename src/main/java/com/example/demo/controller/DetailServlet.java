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

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            BaseDao<Post> postDao = new PostDao(new PostConverter());
           Optional<Post> post = postDao.findById(id);
            if(post.isPresent()){
                req.setAttribute("post",post.get());
                req.getRequestDispatcher("/view/detail.jsp").forward(req,resp);
            }
            req.getRequestDispatcher("/view/404.jsp").forward(req,resp);
        }
    }
}
