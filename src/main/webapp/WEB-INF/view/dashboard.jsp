<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 27.03.2022
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome to dashboard!</h1>


    <div>
        <h2>Navigation bar</h2>
        <ul>
            <li><a href="">dashboard</a></li>
            <li><a href="#">active</a></li>
            <li><a href="#">completed</a></li>
            <li><a href="#">units</a></li>
        </ul>
    </div>

    <div>
        <h1>Recently added tickets</h1>
        <table>
            <thead>
                <tr>title</tr>
                <tr>description</tr>
                <tr>post date</tr>
                <tr>associated unit</tr>
            </thead>
        </table>

        <h1>Recently completed tickets</h1>
        <table>
            <thead>
                <tr>title</tr>
                <tr>description</tr>
                <tr>post date</tr>
                <tr>associated unit</tr>
            </thead>
        </table>
    </div>

</body>
</html>
