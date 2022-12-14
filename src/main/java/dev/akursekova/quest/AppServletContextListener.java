package dev.akursekova.quest;

import dev.akursekova.quest.service.QuestionService;
import dev.akursekova.quest.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(AppServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.info("ServletContextListener started");
        ServletContext context = event.getServletContext();
        QuestionService questionService = QuestionService.getInstance();
        LOG.info("questService has been created: " + questionService);
        context.setAttribute("questService", questionService);
        LOG.info("questService has been added to the servletContext: " + context.getAttribute("questService"));
        context.setAttribute("userRepository", new UserRepository());
        LOG.info("userRepository has been added to the servletContext: " + context.getAttribute("userRepository"));
    }
}
