package dev.akursekova.app;

import dev.akursekova.app.service.QuestionService;
import dev.akursekova.app.repository.UserRepository;
import dev.akursekova.app.subjects.Question;
import dev.akursekova.app.subjects.User;
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
public class QuestServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(QuestServlet.class);

    private QuestionService questionService = null;
    private UserRepository userRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        questionService = (QuestionService) context.getAttribute("questService");
        userRepository = (UserRepository) context.getAttribute("userRepository");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        LOG.info("doGet started. User = " + user + ". Session id = " + session.getId());

        if (!userNameSpecified(session)) {
            System.out.println("doGet. User name mot specified");
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            LOG.info("userName not specified: " + user);
            return;
        }

        if (isFirstQuestion(session) || questRestarted(session)) {
            setFirstQuestion(questionService, session);
            user.setQuestRestarted(false);
        }

        Question currentQuestion = (Question) session.getAttribute("currentQuestion");
        session.setAttribute("currentAnswers", currentQuestion.getAnswers());
        request.getRequestDispatcher("/WEB-INF/question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        LOG.info("doPost started. User = " + user + ". Session id = " + session.getId());

        if (!userNameSpecified(session)) {
            System.out.println("doPost. Username not specified. ");
            initializeNewUser(session, request);
            response.sendRedirect(request.getContextPath() + "/quest");
        } else {
            String buttonClicked = request.getParameter("answer");
            if (buttonClicked.equals("Answer")) {
                Question currentQuestion = (Question) session.getAttribute("currentQuestion");

                if (!answerSelected(request)) {
                    LOG.info("For the question: \'" + currentQuestion.getText() + "\' answer not selected");
                    request.getRequestDispatcher("/WEB-INF/question.jsp").forward(request, response);
                }

                int userChoice = Integer.parseInt(request.getParameter("decision"));
                currentQuestion = currentQuestion.getAnswers().get(userChoice).getNextQuestion();

                if (gameFinished(currentQuestion)) {
                    LOG.info("User " + user.getName() + " finished the game. Number of played games = " + user.getNumberOfGames() + ".");
                    int numberOfGames = user.getNumberOfGames();
                    user.setNumberOfGames(numberOfGames + 1);
                }

                session.setAttribute("currentQuestion", currentQuestion);
                response.sendRedirect(request.getContextPath() + "/quest");
            } else {
                user.setQuestRestarted(true);
                response.sendRedirect(request.getContextPath() + "/quest");
            }
        }
    }

    private boolean questRestarted(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user.getQuestRestarted();
    }

    private void initializeNewUser (HttpSession session, HttpServletRequest request) {
        User user = new User();

        String userName = request.getParameter("name");

        user.setName(userName);
        user.setNumberOfGames(0);
        user.setIpAddress(request.getRemoteAddr());
        user.setQuestRestarted(false);

        userRepository.add(user);
        session.setAttribute("user", user);
        System.out.println("username now = " + user.getName());
        LOG.info("New User registered. User name = " + userName + ". Number of played games = " + user.getNumberOfGames() + ". ");
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

    private void setFirstQuestion(QuestionService questionService, HttpSession session) {
        Question currentQuestion = questionService.init();
        session.setAttribute("currentQuestion", currentQuestion);
    }

    private boolean userNameSpecified(HttpSession session) {
        return (session.getAttribute("user") != null);
    }
}


//todo тех атрибуты на сервисный уровень
//todo лекция от четверга объясняли про юнит тесты. Попробовать начать писать тесты (22.11.)
//todo public final class QuestService { or public class QuestService {