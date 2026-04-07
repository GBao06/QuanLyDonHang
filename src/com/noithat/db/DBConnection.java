

package com.noithat.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=NoiThatDB;encrypt=false";
        String user = "sa";
        String password = "123";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(url, user, password);
    }
}