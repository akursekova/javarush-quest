package dev.akursekova.app;

import dev.akursekova.app.questionService.QuestService;
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
        QuestService questService = QuestService.getInstance();
        LOG.info("questService has been created: " + questService);
        context.setAttribute("questService", questService);
        LOG.info("questService has been added to the servletContext: " + context.getAttribute("questService"));
    }
}
