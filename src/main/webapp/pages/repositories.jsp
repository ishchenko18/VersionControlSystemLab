<%@ page import="com.kpi.java.dtos.ProgramProductDTO" %>
<%@ page import="java.util.List" %>
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
    <jsp:include page="/programs"/>
    <%
        List<ProgramProductDTO> programs = (List) request.getSession().getAttribute("projects");
    %>
</head>
<body>
<div id="container">
    <div>
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <h2 class="header-title">Version Control System</h2>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Repositories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="createRepository.jsp">Create Repository</a>
                </li>
            </ul>
            <a href="#" style="margin-left: 820px; color: #337ab7" onclick="openPage('../index.jsp')">
                <span class="glyphicon glyphicon-home"></span>
            </a>
        </nav>
    </div>
    <div id="body" style="text-align: center">
        <div style="width: 30%; margin-left: 37%">
            <h2 style="text-align: center">List of Repositories</h2>
            <% if (!programs.isEmpty()) { %>
            <ul class="list-group">
                <%
                    for (ProgramProductDTO program : programs) {
                %>
                <li class="list-group-item">
                    <span id="repositoryName" class="repository-name">
                        <%=program.getName()%> - <%=program.getVersion()%>
                    </span>
                    <button type="button" class="btn btn-default pull-right"
                            onclick="removeRepository('<%=program.getId()%>', '<%=program.getName()%>')">
                        <span class="glyphicon glyphicon-trash"/>
                    </button>
                    <button type="button" class="btn btn-default pull-right"
                            onclick="editRepository(<%=program.getId()%>)">
                        <span class="glyphicon glyphicon-edit"/>
                    </button>
                </li>
                <%
                    }
                %>
            </ul>
            <% } else { %>
            <h4 class="no-repositories">You don't have any repositories</h4>
            <% } %>
        </div>
    </div>
    <div id="footer" class="bg-dark">
        <h4>All rights reserved</h4>
    </div>
</div>
</body>
</html>
