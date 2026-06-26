package com.pro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Employee e = new Employee();
        e.setName(name);
        e.setPassword(password);
        e.setEmail(email);
        e.setCountry(country);

        int status = 0;
        response.sendRedirect("view");

        try {
            status = DBOperation.save(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (status > 0) {

            out.println("<html>");
            out.println("<head>");

            out.println("<style>");
            out.println("#toast{");
            out.println("position:fixed;");
            out.println("top:-100px;");
            out.println("left:50%;");
            out.println("transform:translateX(-50%);");
            out.println("background:#28a745;");
            out.println("color:white;");
            out.println("padding:15px 30px;");
            out.println("border-radius:8px;");
            out.println("font-size:18px;");
            out.println("box-shadow:0 4px 10px rgba(0,0,0,0.3);");
            out.println("transition:0.5s;");
            out.println("z-index:9999;");
            out.println("}");
            out.println("</style>");

            out.println("<script>");
            out.println("window.onload=function(){");
            out.println("var t=document.getElementById('toast');");
            out.println("t.style.top='20px';");
            out.println("setTimeout(function(){");
            out.println("t.style.top='-100px';");
            out.println("},3000);");
            out.println("};");
            out.println("</script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div id='toast'> Record Saved Successfully!</div>");

            request.getRequestDispatcher("index.html").include(request, response);

            out.println("</body>");
            out.println("</html>");

        } else {
            out.println("<h3 style='color:red;text-align:center;'>❌ Unable to save record!</h3>");
        }
    }
}