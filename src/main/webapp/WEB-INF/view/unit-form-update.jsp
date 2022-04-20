<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 16.04.2022
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update ${updatedUnit.name}</title>
    <jsp:include page="segments/header.jsp"/>
</head>
<body>

    <jsp:include page="segments/navbar.jsp"/>

    <div class="container-fluid" style="height: 80%">
        <div class="row mt-5">
            <div class="text-center">
                <h1>Update Unit</h1>
                <p>Here you can modify your unit or simply <a class="link-info" href="${pageContext.request.getSession().getAttribute("referer")}">get back</a> to previous page if you changed your mind</p>
            </div>
        </div>

        <div class="row" style="height: 40%">
            <div class="col-4 my-auto mx-auto">
                <form:form action="" method="post" modelAttribute="updatedUnit">
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

</body>
</html>
