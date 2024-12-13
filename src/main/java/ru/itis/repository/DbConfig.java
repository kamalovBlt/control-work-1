package ru.itis.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConfig {
    private static DbConfig instance;
    private static HikariDataSource dataSource;
    private DbConfig() {
        try {
            Class.forName("org.postgresql.Driver");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/oris-control-work-1");
            config.setUsername("postgres");
            config.setPassword("Format100");
            config.setConnectionTimeout(50000);
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);
            Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:db/migrate").load();
            flyway.migrate();
        } catch (ClassNotFoundException | FlywayException e) {
            throw new RuntimeException(e);
        }
    }
    public static DbConfig getInstance() {
        if (instance == null) {
            synchronized (DbConfig.class) {
                if (instance == null) {
                    instance = new DbConfig();
                }
            }
        }
        return instance;
    }
    public synchronized Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public void close() throws SQLException {
        dataSource.close();
    }
}
