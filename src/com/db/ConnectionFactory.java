package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
数据库连接工厂
功能：建立数据库连接
*/
public class ConnectionFactory {
    
    private static ConnectionFactory instance = new ConnectionFactory();
    public static final String URL = "jdbc:mysql://localhost/ManageSystem?serverTimezone=GMT%2B8";
    
    public static final String USER = "root";
    public static final String PASSWORD = "password";//你的密码
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

     
    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS); //加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //连接到数据库
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
}