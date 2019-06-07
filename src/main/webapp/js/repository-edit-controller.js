function openPage(pageURL) {
    window.location.href = pageURL;
}

function updateRepository(id, initialName, initialVersion) {
    var repositoryName = document.getElementById("repositoryNameInput").value;
    var repositoryVersion = document.getElementById("repositoryVersionInput").value;

    if (repositoryName && repositoryVersion) {
        if (repositoryName !== initialName || repositoryVersion !== initialVersion) {
            $.ajax({
                type: "PUT",
                url: "/version_control_system_war_exploded/programs",
                data: '{"id":"' + id + '",' +
                    ' "name":"' + repositoryName + '",' +
                    ' "version": "' + repositoryVersion + '"}',
                success: function () {
                    alert("Repository '" + repositoryName + "' updated in system.");
                },
                error: function () {
                    alert("Repository isn't updated.");
                }
            });
        } else {
            alert("The repository is same. For saving you need to update repository.");
        }
    } else {
        alert("Fields 'Repository Name' and 'Repository Version' should be fulfilled.")
    }
}


function removeFile(fileId, fileName) {
    $.ajax({
        type: "DELETE",
        url: "/version_control_system_war_exploded/files?id=" + fileId,
        success: function () {
            alert("File " + fileName + " successfully deleted from system.");
        },
        error: function () {
            alert("File isn't deleted.");
        }
    });
}

function addFileToRepository(repositoryId) {
    var fileName = document.getElementById("defaultFormFileName").value;
    var version = document.getElementById("defaultFormFileVersion").value;

    if (fileName && version) {
        if (version > 0) {

            $.ajax({
                type: "POST",
                url: "/version_control_system_war_exploded/files",
                data: '{"name":"' + fileName + '",' +
                    ' "version": "' + version + '",' +
                    ' "programProduct": {"id":"' + repositoryId + '"}}',
                success: function () {
                    alert("File '" + fileName + "' successfully created.");
                },
                error: function () {
                    alert("File isn't created.");
                }
            });
        } else {
            alert("Version of file should be positive value.")
        }
    } else {
        alert("Fields 'Name' and 'Version' should be fulfilled.");
    }
}