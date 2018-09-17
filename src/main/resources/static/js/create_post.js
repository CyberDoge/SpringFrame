$(function () {
    $("#preview_image").change(function () {
        let formData = new FormData();
        formData.append('upload', $('#preview_image')[0].files[0]);
        let request = new XMLHttpRequest();
        request.open("POST", "/poster/save-image");
        request.onload = function () {
            if (request.response != null && request.response.uploaded > 0)
                $('#preview_image').after("<br/><img src='" + request.response.url + "'>");
            else alert("error uploading image")
        };
        request.ontimeout = function () {
            alert("time out while uploading image")
        };
        request.timeout = 4000;
        request.responseType = 'json';
        request.send(formData);

        // var response = JSON.parse(request.responseText);

    })
});