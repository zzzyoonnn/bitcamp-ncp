// 기존 jQuery에 기능 추가
ElementBox.prototype.button = function() {
  this.el.forEach((e) => {  // 처음부터 끝까지 배열을 읽을 경우 forEach사용
    e.classList.add("btn1")
  });
};