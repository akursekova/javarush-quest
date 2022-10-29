package dev.akursekova.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// Servlet implementation class FormDataHandle

// Annotation to map the Servlet URL
@WebServlet(name = "ThirdQuestion", value = "/thirdQuestion")
public class ThirdQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String decision = request.getParameter("decision");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        if (decision != null) {
            if (decision.equals("truth")) {
                response.sendRedirect("win.jsp");
            } else {
                response.sendRedirect("tellLie.jsp");
            }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please take decision');");
            out.println("location='thirdQuestion.jsp';");
            out.println("</script>");
        }
    }

}

