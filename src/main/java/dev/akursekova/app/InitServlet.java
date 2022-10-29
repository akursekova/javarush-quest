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
//    private int hitCount = 0;
//
//    public void init(){
//        // Reset hit counter.
//        hitCount = 0;
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Создание новой сессии
        HttpSession session = request.getSession(true);
        //HttpSession session=request.getSession(true);


        System.out.println("start servlet opened " + session.getId());


        String username = request.getParameter("name");
        session.setAttribute("userName", username);
        //System.out.println(session.getAttribute("userName"));

        String ipAddress = request.getRemoteAddr();
        session.setAttribute("ipAddress", ipAddress);

        //hitCount++;
        //System.out.println("test number of restarts " + hitCount);

        session.setAttribute("numberOfGames", 1);
        //System.out.println(hitCount);

        //System.out.println("count from the init servlet " + RestartServlet.count);

        // Get the PrintWriter object to write
        // the response to the text-output stream
//        PrintWriter out = response.getWriter();

        // Print the data
//        out.print("<html><body>");
//        //out.print("<h4>Device created:</h4><br/>");
//        out.print("user Name: "+ session.getAttribute("userName") + "<br/>");
//        out.print("</body></html>");


        // Добавление в сессию параметров поля (нужно будет для хранения состояния между запросами)
        //session.setAttribute("field", field);
        // и значений поля, отсортированных по индексу (нужно для отрисовки крестиков и ноликов)
        //session.setAttribute("data", data);

        // Перенаправление запроса на страницу index.jsp через сервер
        //getServletContext().getRequestDispatcher("/firstQuestion.jsp").forward(req, resp);
        response.sendRedirect("firstQuestion.jsp");
    }
}