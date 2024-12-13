<%@ page import="ru.itis.model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<%
    Order order = (Order) request.getAttribute("order");
%>
<h1>Информация о заказе</h1>
<p>Номер заказа: <%= order.getNumber() %>
</p>
<p>Блюда: <%= order.getDishes() %>
</p>
<p>Номер стола: <%= order.getTableNumber() %>
</p>
<p>Имя официанта: <%= order.getWaiterName() %>
</p>
</body>
</html>
