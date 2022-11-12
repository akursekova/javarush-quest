package dev.akursekova.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("ServletContextListener started");

        //storing connection object as an attribute in ServletContext
        ServletContext context = event.getServletContext();
        QuestService questService = QuestService.getInstance();
        System.out.println("questService has been created: " + questService);
        context.setAttribute("questService", questService);
        System.out.println("questService has been added to the servletContext: " + context.getAttribute("questService"));
    }
}
