<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 15.04.2022
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update Ticket</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/tickets/update" method="post" modelAttribute="updatedTicket">
        <form:hidden path="id"/>

        Title: <form:input path="title"/>
        <form:errors path="title"/>

        Description: <form:input path="description"/>
        <form:errors path="description"/>

        <!-- TODO - object binding does not work :( - converter may be a solution (but how to use one) -->
        Related Unit:
        <form:select path="relatedUnit.id">
            <c:forEach var="unit" items="${units}">
                <form:option value="${unit.id}" label="${unit.name}"/>
            </c:forEach>
        </form:select>

        <form:hidden path="dateAdded"/>

        Completion Status:
        <form:select path="completed">
            <form:option value="true" label="completed"/>
            <form:option value="false" label="active"/>
        </form:select>

        <input type="submit" value="Update"/>
    </form:form>

    <a href="${pageContext.request.getHeader("Referer")}">Back</a>
</body>
</html>
