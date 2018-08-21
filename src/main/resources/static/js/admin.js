$(function () {
    $("#user-list-btn").click(function () {
        send_ajax_for_user_list()
    });
    $("#ban-user").click(function () {
        send_ajax_for_user_ban($("#username-for-action").val())
    })
    $("#unban-user").click(function () {
        send_ajax_for_user_unban($("#username-for-action").val())
    })
    $("#make-admin").click(function () {
        send_ajax_to_make_admin($("#username-for-action").val())
    })
});

function send_ajax_for_user_ban(username) {
    $.ajax({
        type: "POST",
        url: "banUser",
        data: {"username": username},
        success: function (res) {
            alert(res)
        }
    })
}

function send_ajax_to_make_admin(username) {
    $.ajax({
        type: "POST",
        url: "makeAdmin",
        data: {"username": username},
        success: function (res) {
            alert(res)
        }
    })
}
function send_ajax_for_user_unban(username) {
    $.ajax({
        type: "POST",
        url: "unbanUser",
        data: {"username": username},
        success: function (res) {
            alert(res)
        }
    })
}

function send_ajax_for_user_list() {
    $.ajax({
        type: "POST",
        url: "userList",
        success: function (data) {
            createList(data)
        }
    })
}

function createList(data) {
    $("#user-list").empty();
    $("#user-list").append("<tr><td>id</td><td>username</td><td>enabled</td><td>online</td><td>roles</td>");
    $.each(data, function () {
        var roles = "";
        $.each(this.roles, function () {
            roles += "<small>" + this.role + "</small> "
        });
        $("#user-list").append("<tr><td>" + this.id + "</td><td>" + this.username + "</td><td>" + this.enabled + "</td><td>"+this.online+"</td><td>"
            + roles + "</td></tr>");
    })
}