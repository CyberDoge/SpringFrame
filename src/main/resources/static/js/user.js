$(function () {
    $("#change_password").click(function () {
        if($("#confirm").val() != $("#new_password").val()){
            alert("passwords are not confirms ");
            return
        }
        send_ajax_change_password($("#password").val(), $("#new_password").val())
    })
});
function send_ajax_change_password(old_password, new_password) {
    $.ajax({
        type: "POST",
        url: "changePassword",
        data: {
            "old_password": old_password,
            "new_password": new_password
        },
        success: function (res) {
            alert(res);
            $("#password").empty();
            $("#new_password").empty();
            $("#confirm").empty()
        }
    })
}