const memoContainer = document.getElementById('memoContainer');
const deleteButtons = memoContainer.querySelectorAll('[rel="delete"]')
const modifyButtons = memoContainer.querySelectorAll('[rel="modify"]');
const modifyCover = document.getElementById('modifyCover');
const modifyForm = document.getElementById('modifyForm');

deleteButtons.forEach(deleteButton => {
    deleteButton.addEventListener('click', e => {
        e.preventDefault();

        const index = deleteButton.dataset.index;
        // 구조 외우는게 좋음 ↓
        const xhr = new XMLHttpRequest();
        xhr.open('DELETE', `./?index=${index}`); // 요청 준비
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const responseText = xhr.responseText;
                    if (responseText === 'true') {
                        location.href += '';
                    } else {
                        alert('삭제하지 못했습니다.\n\n이미 삭제된 메모입니다.')
                    }
                    ;
                } else {
                    alert('서버와 통신하지 못하였습니다.\n\n잠시 후 다시 시도해주세요.');
                }
            }
        };
        xhr.send();
    });
});
modifyCover.addEventListener('click', () => {
    modifyCover.classList.remove('visible');
    modifyForm.classList.remove('visible');
});
modifyForm.querySelector('[rel="close"]').addEventListener('click', () => {
    modifyCover.classList.remove('visible');
    modifyForm.classList.remove('visible');
});
modifyForm.onsubmit = e => {
    e.preventDefault();
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append(`index`, modifyForm['index'].value);
    formData.append(`text`, modifyForm['text'].value);
    // 여기 부터
    // 요청 방식은 PATCH
    xhr.open('PATCH', `./`)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseText = xhr.responseText;
                if (responseText === 'true') {
                    location.href += '';
                } else {
                    alert('수정하지 못했습니다.')
                }
            }
        }
    };
    // 여기 까지
    xhr.send(formData)
};
modifyButtons.forEach(modifyButton => {
    modifyButton.addEventListener('click', e => {
        e.preventDefault();

        modifyCover.classList.add('visible');
        modifyForm['index'].value = modifyButton.dataset.index;
        modifyForm['text'].value = modifyButton.dataset.text;
        modifyForm['text'].focus();
        modifyForm['text'].select();
        modifyForm.classList.add('visible');
    });
});