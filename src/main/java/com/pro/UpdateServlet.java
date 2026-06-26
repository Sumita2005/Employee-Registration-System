package com.pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String country = request.getParameter("country");

            Connection con = DBOperation.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE emptbl SET name=?, password=?, email=?, country=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, country);
            ps.setInt(5, id);

            ps.executeUpdate();

            response.sendRedirect("view");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}