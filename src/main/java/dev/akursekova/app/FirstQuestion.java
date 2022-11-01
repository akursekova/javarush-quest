package dev.akursekova.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FirstQuestion", value = "/firstQuestion")
public class FirstQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String decision = request.getParameter("decision");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (decision != null) {
            if (decision.equals("accept")) {
            response.sendRedirect("secondQuestion.jsp");
        } else {
            response.sendRedirect("rejectChallenge.jsp");
        }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please take decision');");
            out.println("location='firstQuestion.jsp';");
            out.println("</script>");
        }
    }
}

