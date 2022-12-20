// 1. 태그 찾기
// 2. 태그 만들기
// 3. append()
// 4. 리팩토링
function jQuery(selector) {
  let el = [];  // 생성한 태그나 찾은 태그를 담는 배열

  if (selector.startsWith("<")) { //<는 태그를 만들라는 뜻으로 간주
    el[0] = document.createElement(selector.substring(1, selector.length -1));
  } else {
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      el.push(e);
      }
    }

  // original jQuery가 하는 일
  el.append = function(childBox) { 
    // 자식 태그를 복제해서 각 부모 태그에 붙인다.
    for (let parent of el) {
      // 자식들이 들어있는 상자에서 자식을 한 개씩 꺼내
      // 복제하여 각 부모의 자식으로 붙인다.
      for (let child of childBox) {
        console.log(child);
        parent.appendChild(child.cloneNode(true));
      }
    }
    // child 박스에 들은 자식들은 모두 자식 태그는 제거한다.
    for (let child of childBox) {
      if (child.parentElement != null || child.parentElement != undefined) {
        child.parentElement.removeChild(child);
      }
    }
  };
  // 태그 말고 배열에 집어넣기 , jQuery 리턴 값 필기참고
  return el;
}

var $ = jQuery;
