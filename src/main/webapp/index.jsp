<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Version Control System</title>
    <link rel="icon" href="images/github-octocat.png">
    <link rel="stylesheet" type="text/css" href="css/mainstyles.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <div>
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <h2 class="header-title">Version Control System</h2>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="pages/repositories.jsp">Repositories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Create Repository</a>
                </li>
            </ul>
        </nav>
    </div>
    <div id="body" class="text-align-center">
        <form action="progrmes" method="get">
            <button type="button" class="btn btn-success btn-lg button-start">Get Started</button>
            <input type="submit" value="submit" class="btn btn-success btn-lg button-start">
        </form>
    </div>
    <div id="footer" class="bg-dark">
        <h4>All rights reserved</h4>
    </div>
</div>
</body>
</html>
