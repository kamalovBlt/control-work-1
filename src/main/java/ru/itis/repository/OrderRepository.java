package ru.itis.repository;

import ru.itis.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private final DbConfig dbConfig = DbConfig.getInstance();

    //language=sql
    private static final String SQL_GET_ALL_ORDERS = """
            SELECT *
            FROM orders
            """;

    //language=sql
    private static final String SQL_GET_BY_ID = """
            SELECT *
            FROM orders
            WHERE id = ?
            """;

    public Optional<Order> findById(int id) {
        try (Connection connection = dbConfig.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong("id"),
                        resultSet.getInt("number"),
                        resultSet.getString("dishes"),
                        resultSet.getInt("table_number"),
                        resultSet.getString("waiter_name")
                );
                return Optional.of(order);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<Order>();
        try (Connection connection = dbConfig.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong("id"),
                        resultSet.getInt("number"),
                        resultSet.getString("dishes"),
                        resultSet.getInt("table_number"),
                        resultSet.getString("waiter_name")
                );
                orders.add(order);
            }
            resultSet.close();
            statement.close();
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
