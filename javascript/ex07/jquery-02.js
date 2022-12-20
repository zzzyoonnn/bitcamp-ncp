// 1. 태그 찾기
// 2. 태그 만들기
function jQuery(selector) {
  if (selector.startsWith("<")) { //<는 태그를 만들라는 뜻으로 간주
    return document.createElement(selector.substring(1, selector.length -1));
  } else {
    return document.querySelectorAll(selector);
  }
}

var $ = jQuery;