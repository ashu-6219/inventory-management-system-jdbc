package com.inventory.util;
import java.sql.*;
public class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3307/imsjdbc";
    private static final String username = "root";
    private static final String password = "Krishna@6622";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException("Database Connection Failed");
        }
    }

}
