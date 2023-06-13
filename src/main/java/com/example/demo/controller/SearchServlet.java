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
import java.util.List;
import java.util.stream.Collectors;


public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("searchTerm");
        BaseDao<Post> postDao = new PostDao(new PostConverter());
        List<Post> posts = postDao.getAll();
        if (keyword != null) {
            List<Post> postsByTitle = posts.stream()
                    .filter(post -> post.getTitle().contains(keyword))
                    .collect(Collectors.toList());
            List<Post> result = postsByTitle.size()==0 ? posts : postsByTitle;
            req.setAttribute("posts",result);
        }else {
            req.setAttribute("posts",posts);
        }
        req.getRequestDispatcher("/view/search.jsp").forward(req,resp);
    }
}
