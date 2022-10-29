package dev.akursekova.app;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RestartServlet", value = "/restart")
public class RestartServlet extends HttpServlet {
//    static int count = 1;
//
//    public void init(){
//        count = 1;
//    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //req.getSession().invalidate();
        //resp.sendRedirect("/start");
        //resp.sendRedirect("https://www.geeksforgeeks.org/url-rewriting-using-java-servlet/");
        //System.out.println("one new game");
        //GameCounter.increaseGameCount();
        //System.out.println("now game count is" + GameCounter.getGameCount());

//        if (req.getSession(true).isNew()){
//            System.out.println("new session detected!!!!");
//            init();
//        }
//        count++;
        //req.getSession(true).setAttribute("numberOfGames", count);
        //System.out.println("session count games = " + req.getSession(false).getAttribute("numberOfGames"));
        Integer numberOfGames = (Integer) req.getSession(false).getAttribute("numberOfGames");
        numberOfGames++;
        req.getSession(true).setAttribute("numberOfGames", numberOfGames);
        System.out.println("number of games = " + numberOfGames + " for sessionID = " + req.getSession(true));
        resp.sendRedirect("/javarush_quest_war/firstQuestion.jsp");
    }
}