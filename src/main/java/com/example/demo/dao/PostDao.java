package com.example.demo.dao;

import com.example.demo.config.Database;
import com.example.demo.converter.Converter;
import com.example.demo.dao.query.Query;
import com.example.demo.domain.Author;
import com.example.demo.domain.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PostDao extends BaseDao<Post> {
    Converter<ResultSet, Post> converter;

    public PostDao(Converter converter) {
        this.converter = converter;
    }

    @Override
    public List<Post> getAll() {
        try (Connection connection = Database.getConnectDB()) {
            List<Post> posts = new ArrayList<>();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_ALL_POST);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Post post = converter.convertFromDto(rs);
                    if (post == null) {
                        System.out.println("Post null");
                    }
                    posts.add(post);
                }
            }
            return posts;
        } catch (SQLException e) {
            System.out.println("Error when get all post" + e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Optional<Post> findById(String id) {
        try (Connection connection = Database.getConnectDB()) {
            Post post = null;
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_POST_BY_ID);
                preparedStatement.setString(1,id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                   post = converter.convertFromDto(rs);
                }
            }
            if(post != null) {
                return Optional.of(post);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Error when get all post" + e);
            return Optional.empty();
        }
    }

    @Override
    public boolean add(Post post) {
        boolean flag = true;
        try (Connection connection = Database.getConnectDB()) {
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_POST);
                preparedStatement.setString(1,post.getAuthor());
                preparedStatement.setString(2,post.getTitle());
                preparedStatement.setString(3,post.getDescription());
                preparedStatement.setString(4,post.getContent());
                java.sql.Date currentDate = new java.sql.Date(post.getDate().getTime());
                preparedStatement.setDate(5,currentDate);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error when insert post" + e);
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean delete(String id) {
        try (Connection connection = Database.getConnectDB()) {
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_POST);
                preparedStatement.setString(1,id);
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error when delete post" + e);
            return false;
        }
    }

    @Override
    public boolean edit(String id, Post post) {
        boolean flag = true;
        try (Connection connection = Database.getConnectDB()) {
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(Query.EDIT_POST);
                preparedStatement.setString(1,post.getAuthor());
                preparedStatement.setString(2,post.getTitle());
                preparedStatement.setString(3,post.getDescription());
                preparedStatement.setString(4,post.getContent());
                java.sql.Date currentDate = new java.sql.Date(post.getDate().getTime());
                preparedStatement.setDate(5,currentDate);
                preparedStatement.setString(6, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error when update post" + e);
            flag = false;
        }
        return flag;
    }
}
