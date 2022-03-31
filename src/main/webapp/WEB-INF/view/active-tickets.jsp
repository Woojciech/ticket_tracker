<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 29.03.2022
  Time: 07:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Active Tickets</title>
</head>
<body>
    <!-- TODO use JSP segments -->

    <div>
        <h1>Your active tickets</h1>
        <div>
            <h2>Navigation bar</h2>
            <ul>
                <li><a href="dashboard">dashboard</a></li>
                <li><a href="">active</a></li>
                <li><a href="completed">completed</a></li>
                <li><a href="#">units</a></li>
                <li><a href="add">New ticket</a></li>
            </ul>
        </div>
        <table>
            <thead>
            <tr>
                <td>title</td>
                <td>description</td>
                <td>post date</td>
                <td>associated unit</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ticket" items="${activeTickets}">
                <tr>
                    <td>${ticket.title}</td>
                    <td>${ticket.description}</td>
                    <td>${ticket.dateAdded}</td>
                    <td>${ticket.relatedUnit.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
