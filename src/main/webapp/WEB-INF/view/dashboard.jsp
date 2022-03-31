<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 27.03.2022
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome to dashboard!</h1>


    <div>
        <h2>Navigation bar</h2>
        <ul>
            <li><a href="">dashboard</a></li>
            <li><a href="active">active</a></li>
            <li><a href="completed">completed</a></li>
            <li><a href="#">units</a></li>
            <li><a href="add">New ticket</a></li>
        </ul>
    </div>

    <!-- TODO repair tables -->
    <!-- TODO add logic to move ticket from active to completed and vice versa -->
    <div>
        <h1>Recently added tickets</h1>
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
                <c:forEach var="ticket" items="${dashboardTickets}">
                    <c:if test="${!ticket.completed}">
                        <tr>
                            <td>${ticket.title}</td>
                            <td>${ticket.description}</td>
                            <td>${ticket.dateAdded}</td>
                            <td>${ticket.relatedUnit.name}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div>
        <h1>Recently completed tickets</h1>
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
                <c:forEach var="ticket" items="${dashboardTickets}">
                    <c:if test="${ticket.completed}">
                        <tr>
                            <td>${ticket.title}</td>
                            <td>${ticket.description}</td>
                            <td>${ticket.dateAdded}</td>
                            <td>${ticket.relatedUnit.name}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
