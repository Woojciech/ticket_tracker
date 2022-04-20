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
    <jsp:include page="segments/header.jsp"/>
</head>
<body>

    <jsp:include page="segments/navbar.jsp"/>
    <div class="container-fluid" style="height: 80%">
        <div class="row mt-5">
            <div class="text-center">
                <h1>New Unit</h1>
                <p>Here you can add new unit, enter the details and enjoy!</p>
            </div>
        </div>

        <div class="row" style="height: 50%">
            <div class="col-4 my-auto mx-auto">
                <form:form action="" method="post" modelAttribute="unit">
                    <form:hidden path="id"/>

                    <div class="form-group">
                        <label for="name">Name</label>
                        <form:input class="form-control" id="name" path="name" placeholder="Enter name"/>
                        <form:errors path="name"/>
                    </div>

                    <div class="form-group mt-2">
                        <label for="description">Description</label>
                        <form:input class="form-control" id="description" path="description" placeholder="Enter description"/>
                        <form:errors path="description"/>
                    </div>

                    <div class="form-group mt-2">
                        <button type="button" class="btn btn-dark">Save</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <jsp:include page="segments/previous-page-redirect.jsp"/>
</body>
</html>
