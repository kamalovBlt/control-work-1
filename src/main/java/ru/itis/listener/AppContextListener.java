package ru.itis.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.repository.DbConfig;

import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbConfig.getInstance();
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DbConfig.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
