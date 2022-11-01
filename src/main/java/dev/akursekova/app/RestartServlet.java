package dev.akursekova.app;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RestartServlet", value = "/restart")
public class RestartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer numberOfGames = (Integer) req.getSession(false).getAttribute("numberOfGames");
        numberOfGames++;
        req.getSession(true).setAttribute("numberOfGames", numberOfGames);
        System.out.println("number of games = " + numberOfGames + " for sessionID = " + req.getSession(true));
        resp.sendRedirect("/javarush_quest_war/firstQuestion.jsp");
    }
}