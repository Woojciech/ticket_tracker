<%--
  Created by IntelliJ IDEA.
  User: wojte
  Date: 17.04.2022
  Time: 08:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>

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

    <div class="container">
        <div class="row mt-2">
            <div class="text-center">
                <h1>Dashboard</h1>
                <p>Here you can see the state of most recently added and completed tickets</p>
            </div>

        </div>
        <div class="row mt-5 mb-5">
            <table class="table table-bordered table-hover">
                <caption style="caption-side: top; color: black">
                    <h4>Active Tickets</h4>
                </caption>
                <thead class="table-dark">
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">title</th>
                        <th scope="col">description</th>
                        <th scope="col">post date</th>
                        <th scope="col">associated unit</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>buy rolls</td>
                        <td>buy rolls quickly</td>
                        <td>14 august</td>
                        <td>bakery</td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>buy rolls</td>
                        <td>buy rolls quickly</td>
                        <td>14 august</td>
                        <td>bakery</td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>buy rolls</td>
                        <td>buy rolls quickly</td>
                        <td>14 august</td>
                        <td>bakery</td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>buy rolls</td>
                        <td>buy rolls quickly</td>
                        <td>14 august</td>
                        <td>bakery</td>
                    </tr>
                </tbody>
            </table>


            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" style="color: black" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>

        </div>
        <div class="row mt-5">
            <table class="table table-bordered table-hover">
                <caption style="caption-side: top; color: black">
                    <h4>Completed Tickets</h4>
                </caption>
                <thead class="table-dark">
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">title</th>
                        <th scope="col">description</th>
                        <th scope="col">post date</th>
                        <th scope="col">associated unit</th>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>buy rolls</td>
                    <td>buy rolls quickly</td>
                    <td>14 august</td>
                    <td>bakery</td>
                </tr>
                <tr>
                    <th scope="row">1</th>
                    <td>buy rolls</td>
                    <td>buy rolls quickly</td>
                    <td>14 august</td>
                    <td>bakery</td>
                </tr>
                <tr>
                    <th scope="row">1</th>
                    <td>buy rolls</td>
                    <td>buy rolls quickly</td>
                    <td>14 august</td>
                    <td>bakery</td>
                </tr>
                <tr>
                    <th scope="row">1</th>
                    <td>buy rolls</td>
                    <td>buy rolls quickly</td>
                    <td>14 august</td>
                    <td>bakery</td>
                </tr>
                </tbody>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" style="color: black" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>

        </div>
    </div>
</body>
</html>
