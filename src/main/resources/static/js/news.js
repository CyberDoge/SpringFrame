$(function () {
    let lastId = 0;
    getMorePosts(lastId);
    $(window).scroll(function () {
        if (lastId > 9 && $(window).scrollTop() + $(window).height() > $(document).height() - 100) {
            getMorePosts(lastId)
        }
    });
});

function getMorePosts(lastId) {
    $.ajax({
        url: "/news/addMore",
        method: "post",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(lastId),
        success: function (data) {
            lastId = data[data.length - 1].id;
            for (let i = 0; i < data.length; i++) {
                $("main").prepend('<a href="/news/' + data[i].id + '"><hr><div><h3>' + data[i].header + '</h3><br><div>' + data[i].text + '</div></div></a>');
            }
        },
        error: function (error) {
            console.log(error);
        }
    })
}