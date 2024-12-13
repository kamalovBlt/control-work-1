package ru.itis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.model.Order;
import ru.itis.service.OrderService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/orders/order")
public class OrderByIdServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            Optional<Order> order = orderService.findById(orderId);
            order.ifPresent(value -> request.setAttribute("order", value));
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (ServletException e) {
            throw new ServletException(e);
        }

    }

}
