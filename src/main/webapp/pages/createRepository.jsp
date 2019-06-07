<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Version Control System</title>
    <link rel="icon" href="../images/github-octocat.png">
    <link rel="stylesheet" type="text/css" href="../css/mainstyles.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap-4.3.1-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="../js/repositories-controller.js"></script>
</head>
<body>
<div id="container">
    <div>
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <h2 class="header-title">Version Control System</h2>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="repositories.jsp">Repositories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">Create Repository</a>
                </li>
            </ul>
            <a href="#" style="margin-left: 820px; color: #337ab7" onclick="openPage('../index.jsp')">
                <span class="glyphicon glyphicon-home"></span>
            </a>
        </nav>
    </div>
    <div id="body" style="text-align: center; margin-top: 3%">
        <div>
            <h2>Create New Repository</h2>
            <div style="margin-top: 2%">
                <label for="repositoryNameInput">Name of Repository</label>
                <input id="repositoryNameInput" class="form-control" style="margin-left: 42.5%; width: 15%; margin-bottom: 10px" type="text"
                       name="projectName" placeholder="Repository Name">
                <button type="submit" class="btn btn-primary" onclick="createRepository()">Submit</button>
            </div>
        </div>
    </div>
    <div id="footer" class="bg-dark">
        <h4>All rights reserved</h4>
    </div>
</div>
</body>
</html>
