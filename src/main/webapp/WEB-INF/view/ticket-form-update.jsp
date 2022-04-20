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
    <jsp:include page="segments/header.jsp"/>
</head>
<body>

    <jsp:include page="segments/navbar.jsp"/>

    <div class="container-fluid" style="height: 80%">
        <div class="row mt-5">
            <div class="text-center">
                <h1>Update Ticket</h1>
                <p>Here you can modify your ticket or simply <a class="link-info" href="${pageContext.request.getSession().getAttribute("referer")}">get back</a> to previous page if you changed your mind</p>
            </div>
        </div>

        <div class="row mt-3" style="height: 60%">
            <div class="col-4 my-auto mx-auto">
                <form:form action="${pageContext.request.contextPath}/tickets/update" method="post" modelAttribute="updatedTicket">

                    <form:hidden path="id"/>

                    <div class="form-group">
                        <label for="title">Title</label>
                        <form:input class="form-control" id="title" path="title" placeholder="Enter title"/>
                        <form:errors path="title"/>
                    </div>

                    <div class="form-group mt-2">
                        <label for="description">Description</label>
                        <form:input class="form-control" path="description" id="description" placeholder="Enter description"/>
                        <form:errors path="description"/>
                    </div>

                    <!-- TODO - object binding does not work :( - converter may be a solution (but how to use one) -->
                    <div class="form-group mt-2">
                        <label for="relatedUnit">Related unit</label>
                        <form:select class="form-select" path="relatedUnit.id" id="relatedUnit">
                            <c:forEach var="unit" items="${units}">
                                <form:option value="${unit.id}" label="${unit.name}"/>
                            </c:forEach>
                        </form:select>
                    </div>

                    <form:hidden path="dateAdded"/>

                    <div class="form-group mt-2">
                        <label for="completionStatus">Completion status</label>
                        <form:select class="form-select" path="completed" id="completionStatus">
                            <form:option value="true" label="completed"/>
                            <form:option value="false" label="active"/>
                        </form:select>
                    </div>

                    <div class="form-group mt-2">
                        <button type="button" class="btn btn-dark">Save</button>
                    </div>
                </form:form>
                <!-- <button class="btn btn-info" onclick="${pageContext.request.getSession().getAttribute("referer")}">Back</button> -->
            </div>
        </div>
    </div>

</body>
</html>
