$(function () {
    comments.forEach(function (comment, i, comments) {
        addComment(comment)
    })
    $(".vote_up").click(function (e) {
        div = $(this).parent().parent();
        send_ajax_vote_up(div.attr("id"), div)
    })
    $(".vote_down").click(function (e) {
        div = $(this).parent().parent();
        send_ajax_vote_down(div.attr("id"), div)
    })
});

function addComment(comment) {
    $("#list").append("<div id='" + comment.id + "'><p>" + comment.user.username + "</p><div class='comment'><p>" +
        comment.text + "</p>" + new Date(comment.date).toLocaleDateString("en-US") + " voices: <span class='voices'>" + comment.voices + "</span></div><p>" +
        "<button class='vote_up'>vote up</button><button class='vote_down'>vote down</button></p></div>"
    )
}

function send_ajax_vote_up(id, div) {
    $.ajax({
        type: "POST",
        url: "/news/" + id + "/vote-up",
        success: function (res) {
            if (res) {
                voices = div.find(".voices")
                voices.text(Number(voices.text()) + 1)
            }
        }
    })
}

function send_ajax_vote_down(id, div) {
    $.ajax({
        type: "POST",
        url: "/news/" + id + "/vote-down",
        success: function (res) {
            if (res) {
                voices = div.find(".voices")
                voices.text(Number(voices.text()) - 1)
            }
        }
    })
}