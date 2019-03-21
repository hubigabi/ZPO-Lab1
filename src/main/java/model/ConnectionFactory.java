package model;

import java.sql.*;

public class ConnectionFactory {

    private static final String DB_URL = "jdbc:mysql://localhost/zpo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection con;

    public ConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getCon() {
        return con;
    }

}
