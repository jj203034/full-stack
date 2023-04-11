package com.jjlee.study_jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String PROTOCOL = "jdbc:mariadb";
    private static final String HOST = "localhost";
    private static final String PORT = "33063";
    private static final String USERNAME = "study";
    private static final String PASSWORD = "test1234";

    public static Connection getConnection() throws
            ClassNotFoundException,
            SQLException {
        Class.forName(DatabaseUtil.DRIVER);
        return DriverManager.getConnection(
                String.format("%s://%s:%s",
                        DatabaseUtil.PROTOCOL,
                        DatabaseUtil.HOST,
                        DatabaseUtil.PORT),
                DatabaseUtil.USERNAME,
                DatabaseUtil.PASSWORD);

    }
    private DatabaseUtil() {
        super();
    }
}
