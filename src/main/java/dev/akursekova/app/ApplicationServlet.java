package dev.akursekova.app;

import dev.akursekova.app.questionService.QuestService;
import dev.akursekova.app.questionService.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
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

    private static final Logger LOG = LogManager.getLogger(ApplicationServlet.class);

    private QuestService questService = null;
    private int numberOfGames = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        questService = (QuestService) context.getAttribute("questService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("doGet started");
        HttpSession session = request.getSession(true);

        if (!userNameSpecified(session)) {
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            LOG.info("userName not specified");
            return;
        }

        if (isFirstQuestion(session) || questRestarted(session)) {
            LOG.info("first question or restarted game");
            setFirstQuestion(questService, session);
            session.setAttribute("questRestarted", false);
        }

        Question currentQuestion = (Question) session.getAttribute("currentQuestion");
        session.setAttribute("currentAnswers", currentQuestion.getAnswers());
        request.getRequestDispatcher("/WEB-INF/question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("doPost started");
        HttpSession session = request.getSession(true);
        int numberOfGames;

        if (!userNameSpecified(session)) {
            initializeStatistics(session, request);
            LOG.debug("User specified it's name: user = " + session.getAttribute("user") + ", session_ID = " + session.getId());
            response.sendRedirect(request.getContextPath() + "/quest");
        } else {
            String buttonClicked = request.getParameter("answer");
            if (buttonClicked.equals("Answer")) {
                Question currentQuestion = (Question) session.getAttribute("currentQuestion");

                if (!answerSelected(request)) {
                    LOG.info("answer not selected");
                    request.getRequestDispatcher("/WEB-INF/question.jsp").forward(request, response);
                }

                int userChoice = Integer.parseInt(request.getParameter("decision"));
                currentQuestion = currentQuestion.getAnswers().get(userChoice).getNextQuestion();

                if (gameFinished(currentQuestion)) {
                    numberOfGames = (int) session.getAttribute("numberOfGames") + 1;
                    session.setAttribute("numberOfGames", numberOfGames);
                }

                session.setAttribute("currentQuestion", currentQuestion);
                response.sendRedirect(request.getContextPath() + "/quest");
            } else {
                session.setAttribute("questRestarted", true);
                response.sendRedirect(request.getContextPath() + "/quest");
            }
        }
    }

    private boolean questRestarted(HttpSession session) {
        boolean questRestarted = (Boolean) session.getAttribute("questRestarted");
        return questRestarted ? true : false;
    }

    private void initializeStatistics(HttpSession session, HttpServletRequest request) {
        numberOfGames = 0;
        String user = request.getParameter("name");
        String ipAddress = request.getRemoteAddr();

        session.setAttribute("user", user);
        session.setAttribute("ipAddress", ipAddress);
        session.setAttribute("numberOfGames", numberOfGames);
        session.setAttribute("questRestarted", false);
    }

    private boolean gameFinished(Question question) {
        return (question.isWin() || question.isLoose());
    }

    private boolean answerSelected(HttpServletRequest request) {
        return (request.getParameter("decision") != null);
    }

    private boolean isFirstQuestion(HttpSession session) {
        return (session.getAttribute("currentQuestion") == null);
    }

    private void setFirstQuestion(QuestService questService, HttpSession session) {
        Question currentQuestion = questService.init();
        session.setAttribute("currentQuestion", currentQuestion);
    }

    private boolean userNameSpecified(HttpSession session) {
        return (session.getAttribute("user") != null);
    }
}

