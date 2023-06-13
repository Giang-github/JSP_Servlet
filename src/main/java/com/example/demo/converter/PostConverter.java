package com.example.demo.converter;



import com.example.demo.domain.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class PostConverter extends Converter<ResultSet, Post> {


    public PostConverter(){
        super(PostConverter::convertToPost,null);
    }

    private static Post convertToPost(ResultSet results) {
        try {
            Post post = new Post();
            post.setId(results.getInt("id"));
            post.setAuthor(results.getString("author"));
            post.setTitle(results.getString("title"));
            post.setDescription(results.getString("description"));
            post.setContent(results.getString("content"));
            post.setDate(results.getDate("date"));
            return post;
        }catch (SQLException se){
            System.out.println("Error when convert ResultSet to Post" + se);
            return null;
        }
    }

    private static LocalDate convertToLocalDate(String date) {
        return createDate(date, "dd-MM-yyyy");
    }
    public static LocalDate createDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }
}
