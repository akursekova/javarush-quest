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
@WebServlet(name = "SecondQuestion", value = "/secondQuestion")
public class SecondQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String decision = request.getParameter("decision");
//        System.out.println("User has chosen " + decision + " option");

        // set the content type of response to 'text/html'
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        //response.sendRedirect("test.jsp");

//        if (decision.equals("accept")) {
//            response.sendRedirect("test.jsp");
//        } else if (decision.equals("reject")) {
//            response.sendRedirect("test2.jsp");
//        } else if (decision.equals(null)) {
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('Please choose the option');");
//            out.println("location='firstQuestion.jsp';");
//            out.println("</script>");
//        }

        if (decision != null) {
            if (decision.equals("accept")) {
                response.sendRedirect("thirdQuestion.jsp");
            } else {
                response.sendRedirect("rejectBridge.jsp");
            }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please take decision');");
            out.println("location='secondQuestion.jsp';");
            out.println("</script>");
        }


        // Get the PrintWriter object to write
        // the response to the text-output stream
//        PrintWriter out = response.getWriter();

        // Print the data
//        out.print("<html><body>");
//        out.print("Decision: "+ decision + "<br/>");
//        out.print("</body></html>");




    }

}

