package com.example.demo3.dao;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BoardRepository {
    public BoardRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                    "root",
                    "1234");
            Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM people");
            // Iterate over the results
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " " + resultSet.getString("age"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}