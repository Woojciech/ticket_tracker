<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 17.04.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
    <!--
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand mb-1" href="#">Ticket Tracker</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse ml-auto" id="navbarNavAltMarkup">

                <div class="navbar-nav">
                    <a class="nav-item nav-link" href="#">Dashboard</a>
                    <a class="nav-item nav-link" href="#">Active</a>
                    <a class="nav-item nav-link" href="#">Completed</a>
                    <a class="nav-item nav-link" href="#">Units</a>
                </div>

            </div>

            <div class="collapse navbar-collapse mr-auto justify-content-end">
                <div class="navbar-nav">
                    <a class="nav-item nav-link" href="#">New Ticket</a>
                    <a class="nav-item nav-link" href="#">New Unit</a>
                </div>
            </div>

        </div>
    </nav>
    -->

    <div class="container-fluid" style="height: 80%">
        <div class="row" style="height: 80%">
        <div class="col-4 my-auto mx-auto">
            <form:form action="${pageContext.request.contextPath}/tickets/add" method="post" modelAttribute="ticket">

                <form:hidden path="id"/>

                <div class="form-group">
                    <label for="title">Title</label>
                    <form:input class="form-control" id="title" path="title" placeholder="Enter title"/>
                    <form:errors path="title"/>
                </div>

                <div class="form-group mt-2">
                    <label for="description">Description</label>
                    <form:input class="form-control" path="description" id="description" placeholder="Ticket description"/>
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
        </div>
        </div>
    </div>
</body>
</html>
