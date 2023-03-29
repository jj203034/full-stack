const dialogs = Array.from(document.querySelectorAll('[rel="dialog"]'));

let lastZ = 0;

window.addEventListener('load', () => {
    dialogs.forEach(dialog => {
        const dialogLeft = parseInt(localStorage.getItem(`dialogLeft_${dialog.dataset.dialogId}`) ?? 0);
        const dialogTop = parseInt(localStorage.getItem(`dialogTop_${dialog.dataset.dialogId}`) ?? 0);
        const dialogZ = parseInt(localStorage.getItem(`dialogZ_${dialog.dataset.dialogId}`) ?? 0);
        dialog.style.left = `${dialogLeft}px`;
        dialog.style.top = `${dialogTop}px`;
        dialog.style.zIndex = dialogZ;
    });
});

document.addEventListener('mouseout', () => {
    dialogs.forEach(dialog => {
        dialog.isMoving = false;
    });
});

dialogs.forEach(dialog => {
    dialog.addEventListener('mousedown', () => {
        dialog.style.zIndex = ++lastZ;
    });

    const title = dialog.querySelector(':scope > ._title');
    title.addEventListener('mousedown', e => {
        const rect = dialog.getBoundingClientRect();
        dialog.isMoving = true;
        dialog.mouseOffsetX = e.clientX - rect.left;
        dialog.mouseOffsetY = e.clientY - rect.top;
    });
    title.addEventListener('mouseup', () => {
        dialog.isMoving = false;
        if (dialog.dataset.dialogId) {
            const left = parseInt(dialog.style.left.replace('px', ''));
            const top = parseInt(dialog.style.top.replace('px', ''));
            const z = parseInt(dialog.style.zIndex ?? 0);
            localStorage.setItem(`dialogLeft_${dialog.dataset.dialogId}`, left);
            localStorage.setItem(`dialogTop_${dialog.dataset.dialogId}`, top);
            localStorage.setItem(`dialogZ_${dialog.dataset.dialogId}`, z);
        }
    });
    title.addEventListener('mousemove', e => {
        if (!dialog.isMoving) return;
        let top = Math.max(0, e.clientY - dialog.mouseOffsetY);
        let left = Math.max(0, e.clientX - dialog.mouseOffsetX);
        if (left > document.body.clientWidth - dialog.clientWidth) {
            left = document.body.clientWidth - dialog.clientWidth;
        }
        if (top > document.body.clientHeight - dialog.clientHeight) {
            top = document.body.clientHeight - dialog.clientHeight;
        }
        dialog.style.top = `${top}px`;
        dialog.style.left = `${left}px`;
    });
});