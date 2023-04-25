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
    dialogCover.show();
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    const target1 = document.getElementById('emailVerify')
    const target2 = document.getElementById('emailCode')
    formData.append(`email`, registerForm['email'].value);
    xhr.open('POST', `./registerSendEmail`)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status <= 300) {
                const responseObject = JSON.parse(xhr.responseText); // JSON.parse(x) : 문자열인 x를 js오브젝트(혹은 배열)로 변환해준다.
                switch (responseObject['result']) {
                    case 'success':
                        registerForm['email'].setAttribute('disabled', 'disabled');
                        registerForm['emailSend'].setAttribute('disabled', 'disabled');
                        registerForm['emailCode'].removeAttribute('disabled' );
                        registerForm['emailVerify'].removeAttribute('disabled' );
                        registerForm['emailSalt'].value = responseObject['salt'];
                        dialogCover.show();
                        dialogLayer.show({
                            title: 'SUCCESS',
                            content: '이메일로 인증 번호를 전송했습니다. \n\n 인증번호는 10분간 유효합니다.',
                            onConfirm: e => {
                                dialogCover.hide();
                                dialogLayer.hide();
                                e.preventDefault();
                                registerForm['emailCode'].focus();
                            }
                        });
                        break;
                    case 'failure':
                        dialogCover.show();
                        dialogLayer.show({
                            title: '인증 실패',
                            content: '알 수 없는 이유로 이메일 인증코드 전송에 실패했습니다. \n \n 잠시 후 다시 시도해 주세요.',
                            onConfirm: e => {
                                e.preventDefault();
                                dialogCover.hide();
                                dialogLayer.hide();
                            }
                        });
                        break;
                    case 'failure_email_duplicate':
                        dialogCover.show();
                        dialogLayer.show({
                            title: '이메일 중복',
                            content: '입력하신 이메일 주소는 이미 사용중입니다.',
                            onConfirm: e => {
                                e.preventDefault();
                                dialogCover.hide();
                                dialogLayer.hide();
                                registerForm['email'].focus();
                                registerForm['email'].select();
                            }
                        });
                        break;
                    default:
                }
            } else {
                dialogCover.show();
                dialogLayer.show({
                    title: '통신 오류',
                    content: '서버와 통신하지 못하였습니다. \n\n 잠시후 다시 시도해주세요.',
                    onConfirm: e => {
                        e.preventDefault();
                        dialogCover.hide();
                        dialogLayer.hide();
                    }
                });
            }
        }
    };
    xhr.send(formData)
};