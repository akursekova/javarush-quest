package dev.akursekova.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            session.setAttribute("questRestarted", false);
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

        int numberOfGames;


        //if user not introduced
        if (session.getAttribute("user") == null){
            numberOfGames = 0;
            String user = request.getParameter("name");
            String ipAddress = request.getRemoteAddr();
            initializeStatistics(user, ipAddress, numberOfGames, false, session);

            //to remove
            System.out.println("DOPOST: this is the beginning of the quest");
            System.out.println("username specified. Ready to start the quest");
            System.out.println("it's a new user. GameCounter = " + session.getAttribute("numberOfGames"));
            System.out.println("numberOfGames = " + numberOfGames);
            //to remove

            response.sendRedirect(request.getContextPath() +"/quest");
        }
        //user already introduced himself
        else {
            String buttonClicked = request.getParameter("answer");
            if (buttonClicked.equals("Answer")){
                System.out.println("Answer button clicked");

                Question currentQuestion = (Question) session.getAttribute("currentQuestion");

                if (!answerSelected(request)) {
                    System.out.println("user did not choose anything");
                    request.getRequestDispatcher("/question.jsp").forward(request, response);
                }


                int userChoice = Integer.parseInt(request.getParameter("decision"));
                currentQuestion = currentQuestion.answers.get(userChoice).nextQuestion;


                //To delete
                System.out.println("new current question = " + currentQuestion.text + " " + currentQuestion);
                System.out.println("new current question isWin = " + currentQuestion.isWin);
                System.out.println("new current question isLoose = " + currentQuestion.isLoose);
                //To delete

                if (gameFinished(currentQuestion)){
                    System.out.println("numberOfGames from session BEFORE increment= " + session.getAttribute("numberOfGames"));
                    numberOfGames = (int) session.getAttribute("numberOfGames") + 1;
                    System.out.println("numberOfGames VARIABLE AFTER increment= " + numberOfGames);
                    session.setAttribute("numberOfGames", numberOfGames);
                    System.out.println("numberOfGames from session AFTER increment= " + session.getAttribute("numberOfGames"));
                }

                session.setAttribute("currentQuestion", currentQuestion);
                response.sendRedirect(request.getContextPath() +"/quest");
            }
            //if user clicked restart
            else {
                System.out.println("Restart button clicked");
                session.setAttribute("questRestarted", true);
                response.sendRedirect(request.getContextPath() +"/quest");
            }

        }
    }

    private boolean questRestarted(HttpSession session){
        boolean questRestarted = (Boolean) session.getAttribute("questRestarted");
        return questRestarted? true : false;
    }

    private void initializeStatistics(String user, String ipAddress, int numberOfGames, Boolean questRestarted, HttpSession session){
        session.setAttribute("user", user);
        session.setAttribute("ipAddress", ipAddress);
        session.setAttribute("numberOfGames", numberOfGames);
        session.setAttribute("questRestarted", questRestarted);
    }

    private boolean gameFinished(Question question){
        return (question.isWin == true || question.isLoose == true)? true : false;
    }

    private boolean answerSelected(HttpServletRequest request){
        return (request.getParameter("decision") == null)? false : true;
    }
}

