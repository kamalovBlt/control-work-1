package ru.itis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.service.OrderService;

import java.io.IOException;

@WebServlet("/orders")
public class OrderListPageServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("orders", orderService.findAll());
            req.getRequestDispatcher("/orders.jsp").forward(req, resp);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (ServletException e) {
            throw new ServletException(e);
        }

    }
}
