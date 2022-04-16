<%@ page import="com.suszkolabs.controller.TicketController" %><%--
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
        <!--
        <ul>
            <li>${activeTickets.stream().filter(ticket -> ticket.completed == false).count()} active tickets</li>
            <li>${completedTickets.stream().filter(ticket -> ticket.completed == true).count()} completed tickets</li>


            <li>${pageContext.request.getSession().getAttribute("pageCountActive") * 10} active tickets</li>
            <li>${pageContext.request.getSession().getAttribute("pageCountCompleted") * 10} completed tickets</li>
        </ul>
        -->

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
                <c:forEach var="ticket" items="${activeTickets}">
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
        <ul>
            <li><a href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=1&completed=${pageContext.request.getSession().getAttribute("previousCompletedPage")}">1</a></li>
            <c:forEach var="i" begin="2" end="${pageCountActive}">
                <li><a href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=${i}&completed=${pageContext.request.getSession().getAttribute("previousCompletedPage")}">${i}</a></li>
            </c:forEach>
        </ul>

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
                <c:forEach var="ticket" items="${completedTickets}">
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

        <ul>
            <li><a href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=${pageContext.request.getSession().getAttribute("previousActivePage")}&completed=1">1</a></li>
            <c:forEach var="i" begin="2" end="${pageCountCompleted}">
                <li><a href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=${pageContext.request.getSession().getAttribute("previousActivePage")}&completed=${i}">${i}</a></li>
            </c:forEach>
        </ul>

        <a href="${pageContext.request.contextPath}/tickets/units">back to units</a>
    </div>

</body>
</html>
