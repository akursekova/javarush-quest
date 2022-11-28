package dev.akursekova.app;

import dev.akursekova.app.service.QuestionService;
import dev.akursekova.app.repository.UserRepository;
import dev.akursekova.app.subjects.Answer;
import dev.akursekova.app.subjects.Question;
import dev.akursekova.app.subjects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
    ServletContext servletContext = Mockito.mock(ServletContext.class);
    QuestionService questionService = Mockito.mock(QuestionService.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    HttpSession session = Mockito.mock(HttpSession.class);
    RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
    User user = Mockito.mock(User.class);
    Question question = Mockito.mock(Question.class);
    QuestServlet questServlet;

    @BeforeEach
    void setup() throws ServletException {
        Mockito.when(servletConfig.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getAttribute("questService")).thenReturn(questionService);
        Mockito.when(servletContext.getAttribute("userRepository")).thenReturn(userRepository);
        Mockito.when(request.getSession(true)).thenReturn(session);


        questServlet = new QuestServlet();
        questServlet.init(servletConfig);
    }

    @Test
    void doGet_WhenUserNameNotSpecified() throws ServletException, IOException {
        Mockito.when(request.getAttribute("user")).thenReturn(null);
        Mockito.when(request.getRequestDispatcher(eq("/WEB-INF/index.jsp"))).thenReturn(requestDispatcher);

        questServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doGet_WhenItsAFirstQuestion() throws ServletException, IOException {

        List<Answer> answers = List.of(Answer.builder()
                        .text("answer")
                        .build(),
                Answer.builder()
                        .text("answer")
                        .build());

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(session.getAttribute("currentQuestion")).thenReturn(null, question);
        Mockito.when(user.getQuestRestarted()).thenReturn(false);
        Mockito.when(questionService.init()).thenReturn(question);
        Mockito.when(question.getAnswers()).thenReturn(answers);

        Mockito.when(request.getRequestDispatcher("/WEB-INF/question.jsp")).thenReturn(requestDispatcher);

        questServlet.doGet(request, response);
        verify(session, times(1)).setAttribute(eq("currentQuestion"), eq(question));
        verify(user, times(1)).setQuestRestarted(false);
        verify(session, times(1)).setAttribute(eq("currentAnswers"), eq(answers));
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void doGet_WhenQuestRestarted() throws ServletException, IOException {
        List<Answer> answers = List.of(Answer.builder()
                        .text("answer")
                        .build(),
                Answer.builder()
                        .text("answer")
                        .build());

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(session.getAttribute("currentQuestion")).thenReturn(question);
        Mockito.when(user.getQuestRestarted()).thenReturn(true);
        Mockito.when(questionService.init()).thenReturn(question);
        Mockito.when(question.getAnswers()).thenReturn(answers);
        Mockito.when(request.getRequestDispatcher("/WEB-INF/question.jsp")).thenReturn(requestDispatcher);

        questServlet.doGet(request, response);

        verify(session, times(1)).setAttribute(eq("currentQuestion"), eq(question));
        verify(user, times(1)).setQuestRestarted(false);
        verify(session, times(1)).setAttribute(eq("currentAnswers"), eq(answers));
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void doPost_WhenUserNameNotSpecified() throws ServletException, IOException {
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        Mockito.when(request.getParameter("name")).thenReturn("test");
        Mockito.when(request.getContextPath()).thenReturn("contextPath");
        Mockito.when(request.getRemoteAddr()).thenReturn("0:0:0:0:0:0:0:1");

        User fakeUser = new User();
        fakeUser.setName("test");
        fakeUser.setIpAddress("0:0:0:0:0:0:0:1");
        fakeUser.setNumberOfGames(0);
        fakeUser.setQuestRestarted(false);

        questServlet.doPost(request, response);

        verify(userRepository, times(1)).add(argThat((
                user -> user.getIpAddress().equals("0:0:0:0:0:0:0:1") &&
                        user.getNumberOfGames().equals(0) &&
                        user.getQuestRestarted().equals(false))));
        verify(session, times(1)).setAttribute(eq("user"), eq(fakeUser));
        verify(response).sendRedirect("contextPath" + "/quest");
    }

    @Test
    void doPost_AnswerButtonClickedButOptionNotChosen() throws ServletException, IOException {
        Question fakeQuestion = Mockito.mock(Question.class);
        List<Answer> fakeAnswers = Mockito.mock(List.class);
        Answer firsFakeAnswer = Mockito.mock(Answer.class);
        Question nextFakeQuestion = Mockito.mock(Question.class);

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("answer")).thenReturn("Answer");
        Mockito.when(session.getAttribute("currentQuestion")).thenReturn(fakeQuestion);
        Mockito.when(request.getParameter("decision")).thenReturn(null, "0");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/question.jsp")).thenReturn(requestDispatcher);
        Mockito.when(fakeQuestion.getAnswers()).thenReturn(fakeAnswers);
        Mockito.when(fakeAnswers.get(0)).thenReturn(firsFakeAnswer);
        Mockito.when(firsFakeAnswer.getNextQuestion()).thenReturn(nextFakeQuestion);
        Mockito.when(nextFakeQuestion.isWin()).thenReturn(false);
        Mockito.when(nextFakeQuestion.isLoose()).thenReturn(false);
        Mockito.when(request.getContextPath()).thenReturn("contextPath");

        questServlet.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
        verify(session).setAttribute("currentQuestion", nextFakeQuestion);
        verify(response).sendRedirect("contextPath" + "/quest");
    }

    @Test
    void doPost_AnswerButtonClickedAndItsWinQuestion() throws ServletException, IOException {
        Question fakeQuestion = Mockito.mock(Question.class);
        List<Answer> fakeAnswers = Mockito.mock(List.class);
        Answer firsFakeAnswer = Mockito.mock(Answer.class);
        Question nextFakeQuestion = Mockito.mock(Question.class);

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("answer")).thenReturn("Answer");
        Mockito.when(session.getAttribute("currentQuestion")).thenReturn(fakeQuestion);
        Mockito.when(request.getParameter("decision")).thenReturn("0");
        Mockito.when(fakeQuestion.getAnswers()).thenReturn(fakeAnswers);
        Mockito.when(fakeAnswers.get(0)).thenReturn(firsFakeAnswer);
        Mockito.when(firsFakeAnswer.getNextQuestion()).thenReturn(nextFakeQuestion);
        Mockito.when(nextFakeQuestion.isWin()).thenReturn(true);
        Mockito.when(user.getNumberOfGames()).thenReturn(0);
        Mockito.when(request.getContextPath()).thenReturn("contextPath");

        questServlet.doPost(request, response);

        verify(user, times(1)).setNumberOfGames(1);
        verify(session).setAttribute("currentQuestion", nextFakeQuestion);
        verify(response).sendRedirect("contextPath" + "/quest");
    }

    @Test
    void doPost_AnswerButtonClickedAndItsLooseQuestion() throws ServletException, IOException {
        Question fakeQuestion = Mockito.mock(Question.class);
        List<Answer> fakeAnswers = Mockito.mock(List.class);
        Answer firsFakeAnswer = Mockito.mock(Answer.class);
        Question nextFakeQuestion = Mockito.mock(Question.class);

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("answer")).thenReturn("Answer");
        Mockito.when(session.getAttribute("currentQuestion")).thenReturn(fakeQuestion);
        Mockito.when(request.getParameter("decision")).thenReturn("0");
        Mockito.when(fakeQuestion.getAnswers()).thenReturn(fakeAnswers);
        Mockito.when(fakeAnswers.get(0)).thenReturn(firsFakeAnswer);
        Mockito.when(firsFakeAnswer.getNextQuestion()).thenReturn(nextFakeQuestion);
        Mockito.when(nextFakeQuestion.isLoose()).thenReturn(true);
        Mockito.when(user.getNumberOfGames()).thenReturn(0);
        Mockito.when(request.getContextPath()).thenReturn("contextPath");

        questServlet.doPost(request, response);

        verify(user, times(1)).setNumberOfGames(1);
        verify(session).setAttribute("currentQuestion", nextFakeQuestion);
        verify(response).sendRedirect("contextPath" + "/quest");
    }

    @Test
    void doPost_RestartButtonClicked() throws ServletException, IOException {
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("answer")).thenReturn("Restart");
        Mockito.when(request.getContextPath()).thenReturn("contextPath");

        questServlet.doPost(request, response);

        verify(user).setQuestRestarted(true);
        verify(response).sendRedirect("contextPath" + "/quest");
    }
}