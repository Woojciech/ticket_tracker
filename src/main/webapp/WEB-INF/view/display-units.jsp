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
</head>
<body>

    <div>
        <jsp:include page="segments/navbar.jsp"/>
        <h1>Your Units</h1>
        <a href="units/add">New Unit</a>
        <ul>
            <c:forEach var="unit" items="${units}">
                <li><a href="units/unit?id=${unit.id}">${unit.name}</a></li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>
