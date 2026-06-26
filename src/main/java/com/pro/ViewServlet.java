package com.pro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBOperation.getConnection();

            String keyword = request.getParameter("keyword");

            PreparedStatement ps;

            if (keyword != null && !keyword.isEmpty()) {
                ps = con.prepareStatement("SELECT * FROM emptbl WHERE name LIKE ?");
                ps.setString(1, "%" + keyword + "%");
            } else {
                ps = con.prepareStatement("SELECT * FROM emptbl");
            }

            ResultSet rs = ps.executeQuery();

            out.println("<html><head>");
            out.println("<title>Employee List</title>");

            // ===== CSS =====
            out.println("<style>");
            out.println("body { font-family: Arial; background:#f4f6f9; margin:0; }");

            out.println(".topbar { display:flex; justify-content:space-between; align-items:center; padding:20px; background:white; box-shadow:0px 2px 5px #ccc; }");

            out.println(".title { font-size:22px; font-weight:bold; }");

            out.println(".search-form { display:flex; gap:10px; }");

            out.println(".search-input { padding:8px; width:220px; border:1px solid #ccc; border-radius:5px; }");

            out.println(".search-btn { padding:8px 15px; background:#007bff; color:white; border:none; border-radius:5px; cursor:pointer; }");

            out.println(".container { width:80%; margin:auto; padding-top:20px; }");

            out.println(".card { background:white; padding:15px; margin:10px 0; border-radius:10px; box-shadow:0px 0px 5px gray; }");

            out.println(".btn { padding:5px 10px; margin-right:5px; text-decoration:none; border-radius:5px; color:white; }");

            out.println(".edit { background:green; }");

            out.println(".delete { background:red; }");

            out.println("</style>");

            out.println("</head><body>");

            // ===== TOP BAR =====
            out.println("<div class='topbar'>");

            out.println("<div class='title'>Employee List</div>");

            out.println("<form class='search-form' method='get' action='view'>");
            out.println("<input class='search-input' type='text' name='keyword' placeholder='Search employee by name...'/>");
            out.println("<input class='search-btn' type='submit' value='Search'/>");
            out.println("</form>");

            out.println("</div>");

            // ===== CONTENT =====
            out.println("<div class='container'>");

            while (rs.next()) {

                int id = rs.getInt("id");

                out.println("<div class='card'>");

                out.println("<p><b>ID:</b> " + id + "</p>");
                out.println("<p><b>Name:</b> " + rs.getString("name") + "</p>");
                out.println("<p><b>Password:</b> " + rs.getString("password") + "</p>");
                out.println("<p><b>Email:</b> " + rs.getString("email") + "</p>");
                out.println("<p><b>Country:</b> " + rs.getString("country") + "</p>");

                out.println("<a class='btn edit' href='edit?id=" + id + "'>Edit</a>");
                out.println("<a class='btn delete' href='delete?id=" + id + "' onclick='return confirm(\"Are you sure?\")'>Delete</a>");

                out.println("</div>");
            }

            out.println("</div>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}