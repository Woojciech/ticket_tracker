<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 29.03.2022
  Time: 07:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Active Tickets</title>
    <jsp:include page="segments/header.jsp"/>
</head>
<body>
    <jsp:include page="segments/navbar.jsp"/>

    <div class="container">
        <div class="row mt-2">
            <div class="text-center">
                <h1>Active Tickets</h1>
                <p>Here you can see all of your active tickets regardless of unit they belong to</p>
            </div>
        </div>

        <div class="row mt-2 mb-5">
            <table class="table table-bordered table-hover">
                <caption style="caption-side: top; color: black">
                    <h4>Active Tickets</h4>
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

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/active">1</a></li>
                    <c:forEach var="i" begin="2" end="${pageCountActive}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/active/${i}">${i}</a></li>
                    </c:forEach>
                </ul>
            </nav>

        </div>
    </div>

</body>
</html>
