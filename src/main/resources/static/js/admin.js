$(function () {
    $("#user-list-btn").click(function () {
        send_ajax()
    })
})

function send_ajax() {
    $.ajax({
        type: "POST",
        url: "userList",
        success: function (data) {
            createList(data)
        }
    })
}

function createList(data) {
    $("#user-list").empty()
    $("#user-list").append("<tr><td>id</td><td>username</td><td>enabled</td><td>roles</td>");
    $.each(data, function () {
        var roles = ""
        $.each(this.roles, function () {
            roles += "<small>" + this.role + "</small> "
        })
        $("#user-list").append("<tr><td>" + this.id + "</td><td>" + this.username + "</td><td>" + this.enabled + "</td><td>"
            + roles + "</td></tr>");
    })
}