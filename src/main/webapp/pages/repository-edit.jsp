<%@ page import="com.kpi.java.dtos.ProgramProductDTO" %>
<%@ page import="com.kpi.java.entities.File" %>
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
    <%
        ProgramProductDTO productDTO = (ProgramProductDTO) request.getSession().getAttribute("project");
        List<File> files = productDTO.getFiles();
    %>
    <script type="text/javascript">
        function openPage(pageURL) {
            window.location.href = pageURL;
        }

        function updateRepository() {
            var repositoryName = document.getElementById("repositoryNameInput").value;
            var repositoryVersion = document.getElementById("repositoryVersionInput").value;
            var id = '<%=productDTO.getId()%>';

            if (repositoryName && repositoryVersion) {
                if (!isSameRepository(repositoryName, repositoryVersion)) {
                    $.ajax({
                        type: "PUT",
                        url: "/version_control_system_war_exploded/programs",
                        data: '{"id":"' + id + '",' +
                              ' "name":"' + repositoryName + '",' +
                              ' "version": "' + repositoryVersion + '"}',
                        success: function () {
                            alert("Repository '" + repositoryName + "' saved in system.");
                        },
                        error: function () {
                            alert("Repository isn't created.");
                        }
                    });
                } else {
                    alert("The repository is same.");
                }
            } else {
                alert("Fields 'Repository Name' and 'Repository Version' should be fulfilled.")
            }
        }

        function isSameRepository(name, version) {
            var initialName = '<%=productDTO.getName()%>';
            var initialVersion = '<%=productDTO.getVersion()%>';


            return initialName === name && initialVersion === version;
        }

        function removeFile(fileId) {
            var fileName = document.getElementById("fileName").value;

            $.ajax({
                type: "DELETE",
                url: "/version_control_system_war_exploded/files?id=" + fileId,
                success: function () {
                    alert("Repository '" + fileName + "' successfully deleted from system.");
                },
                error: function () {
                    alert("Repository isn't deleted.");
                }
            });
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
                    <a class="nav-link" href="repositories.jsp">Repositories</a>
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
    <div id="body" style="margin-top: 3%; margin-left: 5%;">
        <div class="div-file-create">
            <h2>Update Repository</h2>
            <div style="margin-top: 2%">
                <label for="repositoryNameInput">Name of Repository</label>
                <input id="repositoryNameInput" class="form-control" style="width: 70%; margin-bottom: 10px" type="text"
                       name="projectName" placeholder="Enter Repository name" value="<%=productDTO.getName()%>"/>

                <label for="repositoryVersionInput">Version of Repository</label>
                <input id="repositoryVersionInput" class="form-control" style="width: 70%; margin-bottom: 10px" type="text"
                       name="projectName" placeholder="Enter Repository version"
                       value="<%=productDTO.getVersion() == null ? "" : productDTO.getVersion()%>"/>
                <button type="submit" class="btn btn-primary" onclick="updateRepository()">Submit</button>
            </div>
        </div>
        <div id="fileListDiv" class="div-file-create">
            <h2 style="text-align: center">List of Files in Repository</h2>
            <% if (files != null && !files.isEmpty()) { %>
            <ul class="list-group">
                <%
                    for (File file : files) {
                %>
                <li class="list-group-item">
                    <span id="fileName" class="repository-name" style="text-align: center">
                        <%=file.getName()%> - <%=file.getVersion()%>
                    </span>
                    <button type="button" class="btn btn-default pull-right" onclick="removeFile(<%=file.getId()%>)">
                        <span class="glyphicon glyphicon-trash"/>
                    </button>
                    <button type="button" class="btn btn-default pull-right" >
                        <span class="glyphicon glyphicon-edit"/>
                    </button>
                </li>
                <%
                    }
                %>
            </ul>
            <% } else { %>
            <h4 class="no-repositories">You don't have any files in this repository.</h4>
            <% } %>
            <div id="addFileButton">
                <button type="button" class="btn btn-success btn-lg button-start" data-toggle="modal" data-target="#modalCreateFileForm">
                    Add New File
                </button>
            </div>
        </div>

        <div class="modal fade" id="modalCreateFileForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Create File</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-envelope prefix grey-text"></i>
                            <label data-error="wrong" data-success="right" for="defaultForm-email">Name</label>
                            <input type="text" id="defaultForm-email" class="form-control validate">
                        </div>

                        <div class="md-form mb-4">
                            <i class="fas fa-lock prefix grey-text"></i>
                            <label data-error="wrong" data-success="right" for="defaultForm-pass">Version</label>
                            <input type="number" id="defaultForm-pass" class="form-control validate">
                        </div>

                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-default">Create</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footer" class="bg-dark">
        <h4>All rights reserved</h4>
    </div>
</div>
</body>
</html>