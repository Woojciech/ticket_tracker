<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 29.03.2022
  Time: 07:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Completed Tickets</title>
</head>
<body>

    <div>
        <jsp:include page="segments/navbar.jsp"/>

        <h1>Your completed tickets</h1>
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
            <c:forEach var="ticket" items="${completedTickets}">
                <tr>
                    <td>${ticket.title}</td>
                    <td>${ticket.description}</td>
                    <td>${ticket.dateAdded}</td>
                    <td>${ticket.relatedUnit.name}</td>
                    <td><a href="delete?id=${ticket.id}">Delete</a></td>
                    <td><a href="update?id=${ticket.id}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/tickets/changeCompletionStatus?id=${ticket.id}&currentStatus=${ticket.completed}">Change completion</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <ul>
            <li><a href="${pageContext.request.contextPath}/tickets/completed">1</a></li>
            <c:forEach var="i" begin="2" end="${pageCountCompleted}">
                <li><a href="${pageContext.request.contextPath}/tickets/completed/${i}">${i}</a></li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>
