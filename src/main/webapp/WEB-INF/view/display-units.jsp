<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 31.03.2022
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Units</title>
    <jsp:include page="segments/header.jsp"/>
</head>
<body>

    <jsp:include page="segments/navbar.jsp"/>

    <div class="container">
        <div class="row mt-2">
            <div class="text-center">
                <h1>Units</h1>
                <p>Here you can see all of your Units, click on any of them for more precise information</p>
            </div>
        </div>

        <div class="row mt-2 mb-5">
            <table class="table table-bordered table-hover">
                <caption style="caption-side: top; color: black">
                    <h4>Active Tickets</h4>
                </caption>
                <thead class="table-dark">
                <tr>
                    <th scope="col">Unit</th>
                    <th scope="col">Delete</th>
                    <th scope="col">Update</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="unit" items="${units}">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/tickets/units/unit?id=${unit.id}&active=1&completed=1">${unit.name}</a></td>
                            <td><a href="delete?id=${unit.id}">Delete</a></td>
                            <td><a href="update?id=${unit.id}">Update</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/units/1">1</a></li>
                    <c:forEach var="i" begin="2" end="${unitPageCount}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tickets/units/${i}">${i}</a></li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>

</body>
</html>
