package com.pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DBOperation.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM emptbl WHERE id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

            response.sendRedirect("view");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}