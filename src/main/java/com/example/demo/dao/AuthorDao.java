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

public class AuthorDao extends BaseDao<Author>{
    Converter<ResultSet, Author> converter;

    public AuthorDao(Converter converter) {
        this.converter = converter;
    }
    @Override
    public List<Author> getAll() {
        try (Connection connection = Database.getConnectDB()) {
            List<Author> authors = new ArrayList<>();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_ALL_AUTHORS);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Author author = converter.convertFromDto(rs);
                    if (author == null) {
                        System.out.println("Author null");
                    }
                    authors.add(author);
                }
            }
            return authors;
        } catch (SQLException e) {
            System.out.println("Error when get all author" + e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Optional findById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean add(Author author) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean edit(String id, Author author) {
        return false;
    }
}
