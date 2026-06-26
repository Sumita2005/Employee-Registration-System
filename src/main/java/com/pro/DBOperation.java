package com.pro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBOperation {

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pune", "root", "05062005");

        return con;
    }

    public static int save(Employee e) throws Exception {

        int status = 0;

        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO emptbl(name, password, email, country) VALUES(?,?,?,?)");

        ps.setString(1, e.getName());
        ps.setString(2, e.getPassword());
        ps.setString(3, e.getEmail());
        ps.setString(4, e.getCountry());

        status = ps.executeUpdate();

        con.close();

        return status;
    }
}