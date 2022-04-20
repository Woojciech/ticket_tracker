<!--
<div>
    <h2>Navigation bar</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/tickets/dashboard">dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/tickets/active">active tickets</a></li>
        <li><a href="${pageContext.request.contextPath}/tickets/completed">completed tickets</a></li>
        <li><a href="${pageContext.request.contextPath}/tickets/units/1">units</a></li>
        <li><a href="${pageContext.request.contextPath}/tickets/add">new ticket</a></li>
        <li><a href="${pageContext.request.contextPath}/tickets/units/add">new unit</a></li>
        <li><a href="#">notification center</a></li>
    </ul>
</div>
-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand mb-1" href="#">Ticket Tracker</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse ml-auto" id="navbarNavAltMarkup">

            <div class="navbar-nav">
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/tickets/dashboard">Dashboard</a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/tickets/active">Active</a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/tickets/completed">Completed</a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/tickets/units/1">Units</a>
            </div>

        </div>

        <div class="collapse navbar-collapse mr-auto justify-content-end">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/tickets/add">New Ticket</a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/tickets/units/add">New Unit</a>
            </div>
        </div>

    </div>
</nav>