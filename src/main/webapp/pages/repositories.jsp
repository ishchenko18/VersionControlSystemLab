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
    <jsp:include page="/programs"/>
    <%
        List<ProgramProductDTO> programs = (List) request.getSession().getAttribute("projects");
    %>
    <script type="text/javascript">
        function openPage(pageURL) {
            window.location.href = pageURL;
        }

        function removeRepository(repositoryId) {
            var repositoryName = document.getElementById("repositoryName").value;

            $.ajax({
                type: "DELETE",
                url: "/version_control_system_war_exploded/programs",
                data: '{"repositoryId":' + repositoryId + '}',
                success: function () {
                    alert("Repository '" + repositoryName + "' successfully deleted from system.");
                },
                error: function (msg) {
                    alert("Repository isn't deleted." + msg);
                }
            });
        }

        function editRepository(repositoryId) {
            $.ajax({
                type: "GET",
                async: false,
                url: "/version_control_system_war_exploded/programs?repositoryId=" + repositoryId
            });

            openPage('/version_control_system_war_exploded/pages/repository-edit.jsp');
        }

/*        function openForm() {
            document.getElementById("edit-repository-popup").style.display = "block";
        }

        function closeForm() {
            document.getElementById("edit-repository-popup").style.display = "none";
        }*/
    </script>
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
                    <span id="repositoryName" class="repository-name"<%--  data-toggle="collapse" data-target="#smth" aria-expanded="false" aria-controls="smth"--%>>
                        <%=program.getName()%> - <%=program.getVersion()%>
                    </span>
                    <button type="button" class="btn btn-default pull-right" onclick="removeRepository(<%=program.getId()%>)">
                        <span class="glyphicon glyphicon-trash"/>
                    </button>
                    <button type="button" class="btn btn-default pull-right" onclick="editRepository(<%=program.getId()%>)">
                        <span class="glyphicon glyphicon-edit"/>
                    </button>
                </li>
<%--                <div id="smth" class="collapse multi-collapse">
                    <a>fgdsfgdfgsd</a>
                </div>--%>
                <%
                    }
                %>
            </ul>
            <% } else { %>
            <h4 class="no-repositories">You don't have any repositories</h4>
            <% } %>

<%--            <div id="edit-repository-popup" class="form-popup">
                <form class="form-container">
                    <h1>Updating</h1>

                    <label for="name"><b>Repository Name</b></label>
                    <input type="text" placeholder="Enter name" id="name" required>

                    <label for="version"><b>Version</b></label>
                    <input type="number" min="1" placeholder="Enter version" id="version" required>

                    <button type="submit" class="btn-form" onclick="">Save</button>
                    <button type="button" class="btn-form cancel" onclick="closeForm()">Close</button>
                </form>
            </div>--%>
        </div>
    </div>
    <div id="footer" class="bg-dark">
        <h4>All rights reserved</h4>
    </div>
</div>
</body>
</html>
