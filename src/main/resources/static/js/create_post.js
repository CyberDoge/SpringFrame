    class UploadAdapter {
    constructor(loader) {
        this.loader = loader;
    }

    upload() {
        server.onUploadProgress(data => {
            loader.uploadTotal = data.total;
            loader.uploaded = data.uploaded;
        });
        return server.upload(loader.file);
    }

    abort() {
        server.abortUpload();
    }
}