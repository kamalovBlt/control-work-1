<%@ page import="ru.itis.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
        Заказы
    </title>
</head>
<body>
<h1>Список заказов</h1>
<ul>
    <%
        List<Order> orderList = (List<Order>) request.getAttribute("orders");
        for (Order order : orderList) {
    %>
    <a href="orders/order?id=<%= order.getId() %>">Заказ номер: <%= order.getNumber() %></a>
    <%
        }
    %>
</ul>
</body>
</html>