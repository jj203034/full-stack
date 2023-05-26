const writeForm = document.getElementById('writeForm');

ClassicEditor.create(writeForm['content'], {
    simpleUpload: {
        uploadUrl: '/article/uploadImage'                              // 업로드 할 주소
    }
});