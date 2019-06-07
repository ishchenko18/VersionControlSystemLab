function openPage(pageURL) {
    window.location.href = pageURL;
}

function removeRepository(repositoryId, repositoryName) {
    $.ajax({
        type: "DELETE",
        url: "/version_control_system_war_exploded/programs",
        data: '{"repositoryId":' + repositoryId + '}',
        success: function () {
            alert("Repository '" + repositoryName + "' successfully deleted from system.");
        },
        error: function () {
            alert("Repository isn't deleted.");
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

function createRepository() {
    var repositoryName = document.getElementById("repositoryNameInput").value;

    if (repositoryName) {
        $.ajax({
            type: "POST",
            url: "/version_control_system_war_exploded/programs",
            data: '{"name": "' + repositoryName + '"}',
            success: function () {
                alert("Repository '" + repositoryName + "' saved in system.");
            },
            error: function () {
                alert("Repository isn't created.");
            }
        });
    } else {
        alert("Please, enter some value.")
    }
}