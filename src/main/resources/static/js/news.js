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
        url: "/news/get-more",
        method: "post",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(lastId),
        success: function (data) {
            lastId = data[data.length - 1].id;
            for (let i = 0; i < data.length; i++) {
                var text = '', img = '';
                if (data[i].previewText != null)
                    text = '<br><div>' + data[i].previewText;
                if (data[i].previewImage != null)
                    img = '<br><img src="' + data[i].previewImage + '">';
                $("main").prepend('<a href="/news/' + data[i].id + '"><hr><div><h3>' + data[i].header + '</h3>' +
                    text + img + '</div></div></a>');
            }
        },
        error: function (error) {
            console.log(error);
        }
    })
}