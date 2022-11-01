package dev.akursekova.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Создание новой сессии
        HttpSession session = request.getSession(true);

        String username = request.getParameter("name");
        session.setAttribute("userName", username);

        String ipAddress = request.getRemoteAddr();
        session.setAttribute("ipAddress", ipAddress);

        session.setAttribute("numberOfGames", 1);

        response.sendRedirect("firstQuestion.jsp");
    }
}