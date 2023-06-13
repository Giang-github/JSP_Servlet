package com.example.demo.converter;

import com.example.demo.domain.Author;
import com.example.demo.domain.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class AuthorConverter extends Converter<ResultSet, Author> {


    public AuthorConverter(){
        super(AuthorConverter::convertToAuthor,null);
    }

    private static Author convertToAuthor(ResultSet results) {
        try {
            Author author = new Author();
            author.setUsername(results.getString("username"));
            author.setPassword(results.getString("password"));
            author.setLastName(results.getString("last_name"));
            author.setFirstName(results.getString("first_name"));
            author.setEmail(results.getString("email"));
            author.setBirthdate(results.getDate("birthdate"));
            author.setAdded(results.getDate("added"));
            return author;
        }catch (SQLException se){
            System.out.println("Error when convert ResultSet to Author" + se);
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
