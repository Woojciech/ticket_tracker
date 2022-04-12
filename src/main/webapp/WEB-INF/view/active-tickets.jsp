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
</head>
<body>
    <!-- TODO use JSP segments -->
    <!-- <form:form action="" method="post"> -->
    <div>
        <jsp:include page="segments/navbar.jsp"/>
        <h1>Your active tickets</h1>
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
            <c:forEach var="ticket" items="${tickets}">
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
        <!-- <input type="submit" value="delete selected"/> -->
        <ul>
            <li><a href="${pageContext.request.contextPath}/tickets/active">1</a></li>
            <c:forEach var="i" begin="2" end="${pagesCount}">
                <li><a href="${pageContext.request.contextPath}/tickets/active/${i}">${i}</a></li>
            </c:forEach>
        </ul>
    </div>
    <!-- </form:form> -->
</body>
</html>
