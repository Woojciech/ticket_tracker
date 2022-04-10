<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 01.04.2022
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Unit</title>
</head>
<body>
    <div>
        <form:form action="" method="post" modelAttribute="unit">
            <form:hidden path="id"/>

            Name: <form:input path="name"/>
            <form:errors path="name"/>

            Description: <form:input path="description"/>
            <form:errors path="description"/>

            <input type="submit" value="Save"/>
        </form:form>

        <a href="${pageContext.request.getHeader("Referer")}">Back</a>
    </div>
</body>
</html>
