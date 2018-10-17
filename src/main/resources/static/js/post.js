$(function () {
    comments.forEach(function (comment, i, comments) {
        addComment(comment)
    })
})

function addComment(comment) {
    $("#list").append("<p>" + comment.userName + "</p><div class='comment'><p>" +
        comment.text + "</p>" + new Date(comment.date).toLocaleDateString("en-US") + " voices: " + comment.voices + "</div>"
    )
}