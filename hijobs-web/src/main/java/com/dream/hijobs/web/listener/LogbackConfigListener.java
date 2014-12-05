package com.dream.hijobs.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author chaney.chan
 * 2014年7月21日
 */
public class LogbackConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            LogbackWebConfigurer.initLogging(sce.getServletContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LogbackWebConfigurer.shutdownLogging(sce.getServletContext());
    }
}
