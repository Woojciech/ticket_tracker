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
    <jsp:include page="segments/header.jsp"/>
</head>
<body>
    <!-- TODO add switch in the corner between ticket/unit dashboard -->
    <jsp:include page="segments/navbar.jsp"/>

    <!-- TODO repair tables -->
    <!-- TODO add logic to move ticket from active to completed and vice versa -->
    <div class="container">
        <div class="row mt-4">
            <div class="text-center">
                <h1>Dashboard</h1>
                <p>Here you can see the state of most recently added and completed tickets</p>
            </div>

        </div>

        <div class="row mt-3 mb-5">
            <table class="table table-bordered table-hover text-center">
                <caption style="caption-side: top; color: black">
                    <h4>Active Tickets</h4>
                </caption>
                <thead class="table-dark">
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Description</th>
                    <th scope="col">Post date</th>
                    <th scope="col">Associated unit</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="ticket" items="${dashboardTickets}" varStatus="counter1">
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

        <div class="row mt-5">
            <table class="table table-bordered table-hover text-center">
                <caption style="caption-side: top; color: black">
                    <h4>Completed Tickets</h4>
                </caption>
                <thead class="table-dark">
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Description</th>
                    <th scope="col">Post date</th>
                    <th scope="col">Associated unit</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="ticket" items="${dashboardTickets}" varStatus="counter">
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
    </div>
    <!--
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
    -->

</body>
</html>
