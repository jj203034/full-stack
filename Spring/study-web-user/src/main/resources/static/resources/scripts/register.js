const addressLayer = document.getElementById('addressLayer');
const registerForm = document.getElementById('registerForm');
addressLayer.show = () => {
    new daum.Postcode({
        oncomplete: (data) => {
            registerForm['addressPostal'].value = data.zonecode;
            registerForm['addressPrimary'].value = data.address;
            registerForm['addressSecondary'].value = '';
            registerForm['addressSecondary'].focus();
            dialogCover.hide();
            addressLayer.hide();
        }
    }).embed(addressLayer);
    addressLayer.classList.add('visible');
};
addressLayer.hide = () => addressLayer.classList.remove('visible');
registerForm.onsubmit = e => {
    e.preventDefault();
    if(!registerForm.classList.contains('step-1')) {
        if (!registerForm['agreeTerm'].checked) {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '서비스 이용약관을 읽고 동의해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                }
            })
            return;
        }
        if (!registerForm['agreePrivacy'].checked) {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '개인정보 처리방침을 읽고 동의해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                }
            })
            return;
        }
        registerForm.classList.remove('step-1');
        registerForm.classList.add('step-2');
    } else if (registerForm.classList.contains('step-2')) {
        alert();
    }
};

registerForm['addressFind'].onclick = () => {
    dialogCover.show();
    addressLayer.show();
};

registerForm['emailSend'].onclick = () => {
    if (registerForm['email'].value === '') {
        dialogLayer.show({
            title: '경고',
            content: '이메일을 입력해 주세요.',
            onConfirm: e => {
                e.preventDefault();
                dialogCover.hide();
                dialogLayer.hide();
                registerForm['email'].focus();
            }
        });
        return;
    }
    //post로 ./registerSendEmail로 요청, insert 확인
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    const target1 = document.getElementById('emailVerify')
    const target2 = document.getElementById('emailCode')
    formData.append(`email`, registerForm['email'].value);
    xhr.open('POST', `./registerSendEmail`)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status <= 300) {
                const responseText = xhr.responseText;
                if (responseText === 'success') {
                    dialogLayer.show({
                        title: 'SUCCESS',
                        content: '이메일로 인증 번호를 전송했습니다. 제한시간은 10분입니다.',
                        onConfirm: e => {
                            e.preventDefault();
                            dialogCover.hide();
                            dialogLayer.hide();
                            registerForm['emailCode'].disabled = false;
                            registerForm['emailVerify'].disabled = false;
                            registerForm['email'].disabled = true;
                            registerForm['emailSend'].disabled = true;
                            registerForm['emailCode'].focus();
                        }
                    })
                } else if (responseText === 'failure') {
                    dialogLayer.show({
                        title: 'FAILURE',
                        content: '알 수 없는 이유로 실패했습니다.',
                        onConfirm: e => {
                            e.preventDefault();
                            dialogCover.hide();
                            dialogLayer.hide();
                            registerForm['email'].focus();
                        }
                    })
                } else if (responseText === 'failure_email_duplicate') {
                    dialogLayer.show({
                        title: 'FAILURE_EMAIL_DUPLICATE',
                        content: '이미 사용중인 이메일입니다.',
                        onConfirm: e => {
                            e.preventDefault();
                            dialogCover.hide();
                            dialogLayer.hide();
                            registerForm['email'].focus();
                        }
                    })
                }
            }
        }
    };
    xhr.send(formData)
};