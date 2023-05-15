const recoverForm = document.getElementById('recoverForm');
recoverForm.show = () => {
    recoverForm.querySelectorAll('input[type="radio"]')[0].checked = true;
    recoverForm['eContact'].value = '';
    recoverForm['eContact'].removeAttribute('disabled');
    recoverForm['eContactSend'].removeAttribute('disabled');
    recoverForm['eContactCode'].value = '';
    recoverForm['eContactCode'].setAttribute('disabled', 'disabled');
    recoverForm['eContactVerify'].setAttribute('disabled', 'disabled');
    recoverForm['eContactSalt'].value = '';
    recoverForm['pEmail'].value = '';

    recoverForm.classList.add('visible');
}
recoverForm.hide = () => {
    coverElement.hide();
    recoverForm.classList.remove('visible');
}

recoverForm.warning = recoverForm.querySelector('[rel="warning"]');
recoverForm.warning.show = (text) => {
    recoverForm.warning.innerText = text;
    recoverForm.warning.classList.add('visible');
}
recoverForm.warning.hide = () => recoverForm.warning.classList.remove('visible');


recoverForm['eContactSend'].onclick = () => {
    recoverForm.warning.hide();
    if (recoverForm['eContact'].value === '') {
        recoverForm.warning.show('연락처를 입력해 주세요.');
        recoverForm['eContact'].focus();
        return;
    }
    if (!new RegExp('^(010)(\\d{8})$').test(recoverForm['eContact'].value)) {
        recoverForm.warning.show('올바른 연락처를 입력해 주세요.');
        recoverForm['eContact'].focus();
        recoverForm['eContact'].select();
        return;
    }
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `/user/contactCodeRec?contact=${recoverForm['eContact'].value}`);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseObject = JSON.parse(xhr.responseText);
                console.log(xhr.responseText);
                switch (responseObject.result) {
                    case `success`:
                        recoverForm.warning.show('입력하신 연락처로 인증번호를 전송하였습니다. 5분 이내로 입력해 주세요.');
                        recoverForm['eContact'].setAttribute('disabled', 'disabled');
                        recoverForm['eContactSend'].setAttribute('disabled', 'disabled');
                        recoverForm['eContactCode'].removeAttribute('disabled');
                        recoverForm['eContactVerify'].removeAttribute('disabled');
                        recoverForm['eContactCode'].focus();
                        recoverForm['eContactSalt'].value = responseObject.salt;
                        break;
                    default:
                        recoverForm.warning.show('서버가 알 수 없는 응답을 반환했습니다. 잠시 후 다시 시도해 주세요.')
                }
            } else {
                recoverForm.warning.show('서버와 통신하지 못하였습니다. 잠시후 다시 시도해 주세요')
            }
        }
    };
    xhr.send();
};

recoverForm['eContactVerify'].onclick = () => {
    recoverForm.warning.hide();
    if (recoverForm['eContactCode'].value === '') {
        recoverForm.warning.show('인증번호를 입력해주세요.');
        recoverForm['eContactCode'].focus();
        return;
    }

    if (!new RegExp('^(\\d{6})$').test(recoverForm['eContactCode'].value)) {
        recoverForm.warning.show('올바른 인증번호를 입력해주세요.');
        recoverForm['eContactCode'].focus();
        recoverForm['eContactCode'].select();
        return;
    }

    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('contact', recoverForm['eContact'].value);
    formData.append('salt', recoverForm['eContactSalt'].value);
    formData.append('code', recoverForm['eContactCode'].value);
    xhr.open('PATCH', '/user/contactCodeRec');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseObject = JSON.parse(xhr.responseText);
                switch (responseObject.result) {
                    case 'failure_expired':
                        recoverForm.warning.show('해당 인증번호는 만료되었습니다. 처음부터 다시 진행해 주세요.');
                        recoverForm['eContact'].removeAttribute('disabled');
                        recoverForm['eContactSend'].removeAttribute('disabled');
                        recoverForm['eContactCode'].setAttribute('disabled', 'disabled');
                        recoverForm['eContactVerify'].setAttribute('disabled', 'disabled');
                        break;
                    case 'success':
                        alert(`이메일 주소는 '${responseObject.email}'입니다. 확인을 누르면 마무리됩니다.`)
                        recoverForm['eContactCode'].setAttribute('disabled', 'disabled');
                        recoverForm['eContactCode'].setAttribute('disabled', 'disabled');
                        recoverForm.hide();
                        coverElement.hide();
                        break;
                    default:
                        recoverForm.warning.show('인증번호가 올바르지 않습니다. 다시 확인해 주세요.')
                        recoverForm['eContactCode'].focus();
                        recoverForm['eContactCode'].select();
                }
            } else {
                recoverForm.warning.show('서버와 통신하지 못하였습니다. 잠시 후 다시 시도해 주세요.');
            }
        }
    };
    xhr.send(formData);
};

recoverForm.onsubmit = e => {
    e.preventDefault();
    if (recoverForm['option'].value === 'password') {
        if (recoverForm['pEmail'].value === '') {
            recoverForm.warning.show('이메일 주소를 입력해 주세요.');
            recoverForm['pEmail'].focus();
            return;
        }
        if (!new RegExp('^(?=.{10,50}$)([\\da-zA-Z\\-_\\.]{5,25})@([\\da-z][\\da-z\\-]*[\\da-z]\\.)?([\\da-z][\\da-z\\-]*[\\da-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(recoverForm['pEmail'].value)) {
            recoverForm.warning.show('올바른 이메일 주소를 입력해 주세요.');
            recoverForm['pEmail'].focusAndSelect();
            return;
        }
        const xhr = new XMLHttpRequest();
        const formData = new FormData();
        formData.append('email', recoverForm['pEmail'].value);
        xhr.open('POST', '/user/recoverPassword');
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const responseObject = JSON.parse(xhr.responseText);
                    switch (responseObject.result) {
                        case 'failure':
                            recoverForm.warning.show('존재하지 않는 이메일입니다. 다시 한번 확인해 주세요.');
                            recoverForm['pEmail'].focusAndSelect();
                            break;
                        case 'success':
                            alert('비밀번호를 재설정할 수 있는 링크를 포함한 이메일을 전송하였습니다. 해당 링크는 한 시간만 유효함으로 유의해주시기 바랍니다.');
                            recoverForm.hide();
                            coverElement.hide();
                            break;
                        default:
                            recoverForm.warning.show('서버가 알 수 없는 응답을 반환하였습니다. 잠시 후 다시 시도해 주세요.');
                    }
                } else {
                    recoverForm.warning.show('서버와 통신하지 못하였습니다. 잠시 후 다시 시도해 주세요.');
                }
            }
        };
        xhr.send(formData);
    }
};

recoverForm.querySelectorAll('[name="cancel"]').forEach(x => x.addEventListener('click', () => {
    recoverForm.hide();
}));