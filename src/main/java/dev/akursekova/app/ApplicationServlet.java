package dev.akursekova.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "ApplicationServlet", value = "/quest")
public class ApplicationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet started");

        ServletContext context = getServletContext();
        QuestService questService = (QuestService) context.getAttribute("questService");
        HttpSession session = request.getSession(true);

        //trigger index.jsp for introduction
        if (session.getAttribute("user") == null){
            System.out.println("DOGET: this is the beginning of the quest");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        //if first question or restarted quest
        Question currentQuestion;
        if (session.getAttribute("currentQuestion") == null || questRestarted(session)){
            System.out.println("this is the very first question or user restarted the quest");
            currentQuestion = questService.q7;
            session.setAttribute("currentQuestion", currentQuestion);
            session.setAttribute("restart", false);
        }

        //not a first question
        System.out.println("current question = " + session.getAttribute("currentQuestion"));
        currentQuestion = (Question) session.getAttribute("currentQuestion");
        System.out.println("current question = " + currentQuestion.text + " " + session.getAttribute("currentQuestion"));

        session.setAttribute("currentAnswers", currentQuestion.answers);
        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost started");
        HttpSession session = request.getSession(true);

        int gameCounter;

        //if user not introduced
        if (session.getAttribute("user") == null){
            System.out.println("DOPOST: this is the beginning of the quest");
            String user = request.getParameter("name");
            session.setAttribute("user", user);
            System.out.println("username specified. Ready to start the quest");

            //STATS
            gameCounter = 0;
            System.out.println("it's a new user. GameCounter = " + session.getAttribute("numberOfGames"));
            String ipAddress = request.getRemoteAddr();
            session.setAttribute("ipAddress", ipAddress);
            session.setAttribute("numberOfGames", gameCounter);
            System.out.println("gameCounter = " + gameCounter);
            //STATS

            response.sendRedirect(request.getContextPath() +"/quest");
        }
        //user already introduced himself
        else {
            String buttonClicked = request.getParameter("answer");
            if (buttonClicked.equals("Answer")){
                System.out.println("Answer button clicked");
                Question currentQuestion = (Question) session.getAttribute("currentQuestion");

                //if user didn't make the choice
                if (request.getParameter("decision") == null) {
                    System.out.println("user did not choose anything");
                    request.getRequestDispatcher("/question.jsp").forward(request, response);
                }


                int userChoice = Integer.parseInt(request.getParameter("decision"));
                currentQuestion = currentQuestion.answers.get(userChoice).nextQuestion;


                System.out.println("new current question = " + currentQuestion.text + " " + currentQuestion);
                System.out.println("new current question isWin = " + currentQuestion.isWin);
                System.out.println("new current question isLoose = " + currentQuestion.isLoose);

                //TEST
                if (currentQuestion.isWin == true || currentQuestion.isLoose == true){
                    System.out.println("gameCounter from session BEFORE increment= " + session.getAttribute("numberOfGames"));
                    //System.out.println("gameCounter VARIABLE BEFORE increment= " + gameCounter);
                    gameCounter = (int) session.getAttribute("numberOfGames") + 1;
                    System.out.println("gameCounter VARIABLE AFTER increment= " + gameCounter);
                    session.setAttribute("numberOfGames", gameCounter);
                    System.out.println("gameCounter from session AFTER increment= " + session.getAttribute("numberOfGames"));
                }
                //TEST

                session.setAttribute("currentQuestion", currentQuestion);
                response.sendRedirect(request.getContextPath() +"/quest");
            }
            //if user clicked restart
            else {
                System.out.println("Restart button clicked");
                session.setAttribute("restart", true);
                response.sendRedirect(request.getContextPath() +"/quest");
            }

        }
    }

    private boolean questRestarted(HttpSession session){
        if(session.getAttribute("restart") != null){
            boolean questRestarted = (Boolean) session.getAttribute("restart");
            if (questRestarted) {
                System.out.println("user wants to restart the game");
                return true;
            }
        }
        return false;
    }

}


//todo стили
