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
    if (!registerForm.classList.contains('step-1')) {
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
        if (registerForm['nickname'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '별명을 입력해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['nickname'].focus();
                }
            })
            return;
        }
        if (registerForm['password'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '비밀번호를 입력해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['password'].focus();
                }
            })
            return;
        }
        if (registerForm['passwordCheck'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '비밀번호를 다시 한번 입력해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['passwordCheck'].focus();
                }
            })
            return;
        }
        if (registerForm['password'].value != registerForm['passwordCheck'].value) {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '비밀번호가 일치하지 않습니다.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['passwordCheck'].focus();
                }
            })
            return;
        }
        if (registerForm['addressPrimary'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '주소찾기를 통해 주소를 입력해 주세요',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['addressPrimary'].focus();
                }
            })
            return;
        }
        if (registerForm['addressSecondary'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '상세 주소를 입력해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['addressSecondary'].focus();
                }
            })
            return;
        }
        if (registerForm['gender'].value != 'F' && registerForm['gender'].value != 'M') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '성별을 선택해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['gender'].focus();
                }
            })
            return;
        }
        if (registerForm['name'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '실명을 입력해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['name'].focus();
                }
            })
            return;
        }
        if (registerForm['birth'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '생년월일을 선택해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['birth'].focus();
                }
            })
            return;
        }
        if (registerForm['contact'].value === '') {
            dialogCover.show();
            dialogLayer.show({
                title: '경고',
                content: '연락처를 입력해 주세요.',
                onConfirm: e => {
                    e.preventDefault();
                    dialogCover.hide();
                    dialogLayer.hide();
                    registerForm['contact'].focus();
                }
            })
            return;
        }
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
                        registerForm['emailCode'].removeAttribute('disabled');
                        registerForm['emailVerify'].removeAttribute('disabled');
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

registerForm['emailVerify'].onclick = () => {
    if (registerForm['emailCode'].value === '') {
        dialogCover.show();
        dialogLayer.show({
            title: '경고',
            content: '이메일 인증번호를 입력해 주세요.',
            onConfirm: e => {
                e.preventDefault();
                dialogCover.hide();
                dialogLayer.hide();
                registerForm['emailCode'].focus();
            }
        })
        return;
    }
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append(`email`, registerForm['email'].value);
    formData.append(`code`, registerForm['emailCode'].value);
    formData.append(`salt`, registerForm['emailSalt'].value);
    xhr.open('POST', './registerVerifyEmail');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status <= 300) {
                const responseObject = JSON.parse(xhr.responseText);
                switch (responseObject['result']) {
                    case 'failure':
                        dialogCover.show();
                        dialogLayer.show({
                            title: '실패',
                            content: '인증에 실패했습니다.\n \n 다시 입력해 주세요.',
                            onConfirm: e => {
                                e.preventDefault();
                                dialogCover.hide();
                                dialogLayer.hide();
                                registerForm['email'].removeAttribute('disabled');
                                registerForm['emailSend'].removeAttribute('disabled');
                            }
                        })
                        break;
                    case 'failure_expired':
                        dialogCover.show();
                        dialogLayer.show({
                            title: '실패',
                            content: '인증시간이 초과되었습니다.\n 인증번호를 다시 받아주세요.',
                            onConfirm: e => {
                                e.preventDefault();
                                dialogCover.hide();
                                dialogLayer.hide();
                                registerForm['email'].removeAttribute('disabled');
                                registerForm['emailSend'].removeAttribute('disabled');
                            }
                        })
                        break;
                    case 'success':
                        registerForm['email'].removeAttribute('disabled');
                        registerForm['emailSend'].removeAttribute('disabled');
                        registerForm['emailCode'].setAttribute('disabled', 'disabled');
                        registerForm['emailVerify'].setAttribute('disabled', 'disabled');
                        dialogCover.show();
                        dialogLayer.show({
                            title: '성공',
                            content: '인증이 완료되었습니다.',
                            onConfirm: e => {
                                e.preventDefault();
                                dialogCover.hide();
                                dialogLayer.hide();
                            }
                        })
                        break;
                    default:
                }
            }
        }
    }
    xhr.send(formData)
}

