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
    <jsp:include page="segments/header.jsp"/>
</head>
<body>

    <jsp:include page="segments/navbar.jsp"/>

    <div class="container">
        <div class="row mt-2">
            <div class="text-center">
                <h1>Unit - <a class="" href="${pageContext.request.contextPath}/tickets/units/update?id=${unit.id}">${unit.name}</a> in details</h1>
                <p>${unit.description}</p>
                <p>Click on Unit's name in order to update, you can also <a href="${pageContext.request.contextPath}/tickets/units/${pageContext.request.getSession().getAttribute("referencePage")}">get back</a> to units</p>

            </div>
        </div>

        <div class="row mt-2 mb-5">
            <table class="table table-bordered table-hover">
                <caption style="caption-side: top; color: black">
                    <h4>Unit's Active Tickets</h4>
                </caption>
                <thead class="table-dark">
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Description</th>
                    <th scope="col">Post date</th>
                    <th scope="col">Associated unit</th>
                    <th scope="col">Delete</th>
                    <th scope="col">Update</th>
                    <th scope="col">Completion status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ticket" items="${activeTickets}">
                    <tr>
                        <td>${ticket.title}</td>
                        <td>${ticket.description}</td>
                        <td>${ticket.dateAdded}</td>
                        <td>${ticket.relatedUnit.name}</td>
                        <td><a href="${pageContext.request.contextPath}/tickets/delete?id=${ticket.id}">Delete</a></td>
                        <td><a href="${pageContext.request.contextPath}/tickets/update?id=${ticket.id}">Update</a></td>
                        <td><a href="${pageContext.request.contextPath}/tickets/changeCompletionStatus?id=${ticket.id}&currentStatus=${ticket.completed}">Change</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=1&completed=${pageContext.request.getSession().getAttribute("previousCompletedPage")}">1</a></li>
                    <c:forEach var="i" begin="2" end="${pageCountActive}">
                        <li class="page-item"><a href class="page-link"="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=${i}&completed=${pageContext.request.getSession().getAttribute("previousCompletedPage")}">${i}</a></li>
                    </c:forEach>
                </ul>
            </nav>

        </div>

        <div class="row mt-2 mb-5">
            <table class="table table-bordered table-hover">
                <caption style="caption-side: top; color: black">
                    <h4>Unit's Completed Tickets</h4>
                </caption>
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Description</th>
                        <th scope="col">Post date</th>
                        <th scope="col">Associated unit</th>
                        <th scope="col">Delete</th>
                        <th scope="col">Update</th>
                        <th scope="col">Completion status</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="ticket" items="${completedTickets}">
                    <tr>
                        <td>${ticket.title}</td>
                        <td>${ticket.description}</td>
                        <td>${ticket.dateAdded}</td>
                        <td>${ticket.relatedUnit.name}</td>
                        <td><a href="${pageContext.request.contextPath}/tickets/delete?id=${ticket.id}">Delete</a></td>
                        <td><a href="${pageContext.request.contextPath}/tickets/update?id=${ticket.id}">Update</a></td>
                        <td><a href="${pageContext.request.contextPath}/tickets/changeCompletionStatus?id=${ticket.id}&currentStatus=${ticket.completed}">Change</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=${pageContext.request.getSession().getAttribute("previousActivePage")}&completed=1">1</a></li>
                    <c:forEach var="i" begin="2" end="${pageCountCompleted}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=${pageContext.request.getSession().getAttribute("previousActivePage")}&completed=${i}">${i}</a></li>
                    </c:forEach>
                </ul>
            </nav>

        </div>
    </div>

</body>
</html>
