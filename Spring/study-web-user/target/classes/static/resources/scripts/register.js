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
};

registerForm['addressFind'].onclick = () => {
    addressLayer.show();
};