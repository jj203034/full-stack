// HTML 전체에 적용가능
HTMLElement.prototype.show = function () {
    this.classList.add('visible');
}

HTMLElement.prototype.hide = function () {
    this.classList.remove('visible');
}

HTMLInputElement.prototype.focusAndSelect = function () {
    this.focus();
    this.select();
}