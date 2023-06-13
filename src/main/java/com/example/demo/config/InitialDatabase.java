package com.example.demo.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitialDatabase {
    public static void Initial() {
//        Create table if not exiests
        // Chuyển thành final
        String createAuthors = "CREATE TABLE IF NOT EXISTS `authors` (" +
                "  `username` varchar(30) NOT NULL PRIMARY KEY, " +
                "  `password` varchar(60), " +
                "  `first_name` varchar(50) DEFAULT NULL, " +
                "  `last_name` varchar(50) DEFAULT NULL, " +
                "  `email` varchar(100) DEFAULT NULL, " +
                "  `birthdate` date DEFAULT NULL, " +
                "  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ")";
        String createPosts = "CREATE TABLE IF NOT EXISTS `posts` (" +
                "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "  `author` varchar(30) DEFAULT NULL, " +
                "  `title` varchar(255) DEFAULT NULL, " +
                "  `description` varchar(500) DEFAULT NULL, " +
                "  `content` text DEFAULT NULL, " +
                "  `date` date DEFAULT NULL, " +
                "  CONSTRAINT `author` FOREIGN KEY (`author`) REFERENCES `authors` (`username`) " +
                ")";
        String createAuthorities = "CREATE TABLE IF NOT EXISTS `authorities` (" +
                "  `authority` varchar(256) DEFAULT NULL, " +
                "  `username` varchar(30) DEFAULT NULL, " +
                "  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `authors` (`username`) " +
                ")";
//       Delete data table
        String deleteAuthor = "DELETE FROM `authors`";
        String deletePost = "DELETE FROM `posts` ";
        String deleteAuthorities = "DELETE FROM authorities ";
        String resetID = "ALTER TABLE posts AUTO_INCREMENT = 1";
//        Insert data in every table
        String addAuthor = "INSERT INTO `authors` (`username`, `password`, `first_name`, `last_name`, `email`, `birthdate`) VALUES " +
                "('gang', '1234', 'John', 'Smith', 'gang@example.com', '1990-01-01'), " +
                "('gang1', '1234', 'Jane', 'Doe1', 'gang1@example.com', '1995-02-15'), " +
                "('gang2', '1234', 'John', 'Doe2', 'gang2@example.com', '1993-08-22'), " +
                "('gang3', '1234', 'John', 'Doe3', 'gang3@example.com', '1993-08-22'), " +
                "('gang4', '1234', 'John', 'Doe4', 'gang4@example.com', '1993-08-22')";

        String addPosts = "INSERT INTO `posts` (`author`, `title`, `description`, `content`, `date`) VALUES " +
                "('gang', 'First Post', 'Description of first post', 'Content of first post', '2023-04-25'), " +
                "('gang1', 'Second Post', 'Description of second post', 'Content of second post', '2023-04-26'), " +
                "('gang2', 'Third Post', 'Description of third post', 'Content of third post', '2023-04-27'), " +
                "('gang3', 'Fourth Post', 'Description of fourth post', 'Content of fourth post', '2023-04-28'), " +
                "('gang4', 'Fifth Post', 'Description of fifth post', 'Content of fifth post', '2023-04-29'), " +
                "('gang', 'Sixth Post', 'Description of sixth post', 'Content of sixth post', '2023-04-30')";


        String addAuthorities = "INSERT INTO `authorities` (`authority`, `username`) VALUES " +
                "('ADMIN', 'gang'), " +
                "('USER', 'gang1'), " +
                "('USER', 'gang2'), " +
                "('USER', 'gang3'), " +
                "('USER', 'gang4')";

        try (Connection connection = Database.getConnectDB();
             PreparedStatement authors = connection.prepareStatement(createAuthors);
             PreparedStatement posts = connection.prepareStatement(createPosts);
             PreparedStatement authorities = connection.prepareStatement(createAuthorities);
             PreparedStatement deleteTableAuthor = connection.prepareStatement(deleteAuthor);
             PreparedStatement deleteTablePost = connection.prepareStatement(deletePost);
             PreparedStatement deleteTableAuthorities = connection.prepareStatement(deleteAuthorities);
             PreparedStatement resetIDPost = connection.prepareStatement(resetID);
             PreparedStatement insertAuthor = connection.prepareStatement(addAuthor);
             PreparedStatement insertPost = connection.prepareStatement(addPosts);
             PreparedStatement insertAuthorities = connection.prepareStatement(addAuthorities)
        ){
            authors.executeUpdate();
            posts.executeUpdate();
            authorities.executeUpdate();
            deleteTablePost.executeUpdate();
            deleteTableAuthorities.executeUpdate();
            deleteTableAuthor.executeUpdate();
            resetIDPost.executeUpdate();
            insertAuthor.executeUpdate();
            insertPost.executeUpdate();
            insertAuthorities.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
