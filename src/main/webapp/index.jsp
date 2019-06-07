<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Version Control System</title>
    <link rel="icon" href="images/github-octocat.png">
    <link rel="stylesheet" type="text/css" href="css/mainstyles.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function openPage(pageURL) {
            window.location.href = pageURL;
        }
    </script>
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
                    <a class="nav-link" href="pages/createRepository.jsp">Create Repository</a>
                </li>
            </ul>
            <a href="#" style="margin-left: 820px">
                <span class="glyphicon glyphicon-home"></span>
            </a>
        </nav>
    </div>
    <div id="body" class="text-align-center">
        <button type="button" class="btn btn-success btn-lg button-start" onclick="openPage('pages/repositories.jsp')">
            Get Started
        </button>
    </div>
    <div id="footer" class="bg-dark">
        <h4>All rights reserved</h4>
    </div>
</div>
</body>
</html>
