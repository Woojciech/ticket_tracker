<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 31.03.2022
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${unit.name}</title>
</head>
<body>
    <div>
        <h1>Unit - ${unit.name}</h1>

        <h3>${unit.description}</h3>
        <ul>
            <li>${unit.relatedTickets.stream().filter(ticket -> ticket.completed == false).count()} active tickets</li>
            <li>${unit.relatedTickets.stream().filter(ticket -> ticket.completed == true).count()} completed tickets</li>
        </ul>

        <h2>Related active tickets</h2>
        <table>
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Description</td>
                    <td>Date added</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ticket" items="${unit.relatedTickets}">
                    <c:if test="${ticket.completed eq false}">
                        <tr>
                            <td>${ticket.title}</td>
                            <td>${ticket.description}</td>
                            <td>${ticket.dateAdded}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>

        <h2>Related completed tickets</h2>
        <table>
            <thead>
            <tr>
                <td>Title</td>
                <td>Description</td>
                <td>Date added</td>
                <td></td>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="ticket" items="${unit.relatedTickets}">
                    <c:if test="${ticket.completed}">
                        <tr>
                            <td>${ticket.title}</td>
                            <td>${ticket.description}</td>
                            <td>${ticket.dateAdded}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
