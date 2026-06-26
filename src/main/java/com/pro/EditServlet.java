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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    int id = Integer.parseInt(request.getParameter("id"));

	    try {
	        Connection con = DBOperation.getConnection();

	        PreparedStatement ps = con.prepareStatement("SELECT * FROM emptbl WHERE id=?");
	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        rs.next();

	        out.println("<html><head>");
	        out.println("<title>Edit Employee</title>");

	        out.println("<style>");
	        out.println("body { font-family: Arial; background: #f4f6f9; }");
	        out.println(".box { width: 400px; margin: 50px auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px #aaa; }");
	        out.println("h2 { text-align: center; color: #333; }");
	        out.println("input[type=text], input[type=password] { width: 100%; padding: 10px; margin: 8px 0; border: 1px solid #ccc; border-radius: 5px; }");
	        out.println("input[type=submit] { width: 100%; background: #28a745; color: white; padding: 10px; border: none; border-radius: 5px; cursor: pointer; }");
	        out.println("input[type=submit]:hover { background: #218838; }");
	        out.println(".back { display:block; text-align:center; margin-top:10px; text-decoration:none; }");
	        out.println("</style>");

	        out.println("</head><body>");

	        // ===== FORM START =====
	        out.println("<div class='box'>");
	        out.println("<h2>Edit Employee</h2>");

	        out.println("<form action='update' method='post'>");

	        out.println("<input type='hidden' name='id' value='" + id + "'/>");

	        out.println("Name");
	        out.println("<input type='text' name='name' value='" + rs.getString("name") + "'/>");

	        out.println("Password");
	        out.println("<input type='text' name='password' value='" + rs.getString("password") + "'/>");

	        out.println("Email");
	        out.println("<input type='text' name='email' value='" + rs.getString("email") + "'/>");

	        out.println("Country");
	        out.println("<input type='text' name='country' value='" + rs.getString("country") + "'/>");

	        out.println("<input type='submit' value='Update Employee'/>");

	        out.println("</form>");

	        out.println("<a class='back' href='view'>← Back to Employee List</a>");
	        out.println("</div>");

	        out.println("</body></html>");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}