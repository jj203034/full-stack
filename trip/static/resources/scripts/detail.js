const detailForm = document.getElementById('detailForm');
detailForm.show = () => {
    detailForm.classList.add('visible');
}
detailForm.hide = () => {
    detailForm.classList.remove('visible');
}

const coverElement = document.getElementById('cover');
coverElement.show = (f) => {
    coverElement.classList.add('visible');
    coverElement.onclick = f;
}
coverElement.hide = () => {
    coverElement.classList.remove('visible');
    coverElement.onclick = undefined;
}

document.body.querySelectorAll('[data-action]').forEach(element => {
    element.addEventListener('click', evt => {
        const action = element.dataset.action;
        switch (action) {
            case 'showDetail':
                coverElement.show(() => {
                    coverElement.hide();
                    detailForm.hide();
                });
                detailForm.show();
                break;
        }
    });
})
