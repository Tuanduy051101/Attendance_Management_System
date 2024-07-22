package com.company.attendance.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL kết nối đến cơ sở dữ liệu Oracle
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    // Tên đăng nhập cơ sở dữ liệu
    private static final String USER = "your_username";
    // Mật khẩu cơ sở dữ liệu
    private static final String PASSWORD = "your_password";

    // Phương thức để lấy kết nối đến cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}