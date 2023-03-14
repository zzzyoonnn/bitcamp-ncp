"use strict";
console.log("시작됨!");

// 리액트 코드 안에서만 사용할 수 있는 전용 태그 = 리액트 엘리먼트 = 리액트 컴포넌트
// 좋아요 버튼 리액트 컴포넌트 정의
class LikeButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      liked: false,
    };
  }

  // 이 컴포넌트가 어떤 태그를 생성할 지 정의한다.
  // 부모 컴포넌트에서 순차적으로 내려오면서 호출된다.
  // 하는 일은 HTML 태그를 생성하는 일을 한다.
  // 또는 하위 컴포넌트를 실행하는 일을 한다.
  render() {
    if (this.state.liked) {
      return "좋아합니다!";
    }

    return (
      <button onClick={() => this.setState({ liked: true })}>좋아요</button>
    );
  }
}

document.querySelectorAll(".like-container").forEach((likeContainer) => {
  const root = ReactDOM.createRoot(likeContainer);
  root.render(React.createElement(LikeButton));
});
console.log("종료됨!");
