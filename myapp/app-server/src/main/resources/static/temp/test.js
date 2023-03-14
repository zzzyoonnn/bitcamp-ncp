"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

console.log("시작됨!");

// 리액트 코드 안에서만 사용할 수 있는 전용 태그 = 리액트 엘리먼트 = 리액트 컴포넌트
// 좋아요 버튼 리액트 컴포넌트 정의

var LikeButton = function (_React$Component) {
  _inherits(LikeButton, _React$Component);

  function LikeButton(props) {
    _classCallCheck(this, LikeButton);

    var _this = _possibleConstructorReturn(this, (LikeButton.__proto__ || Object.getPrototypeOf(LikeButton)).call(this, props));

    _this.state = {
      liked: false
    };
    return _this;
  }

  // 이 컴포넌트가 어떤 태그를 생성할 지 정의한다.
  // 부모 컴포넌트에서 순차적으로 내려오면서 호출된다.
  // 하는 일은 HTML 태그를 생성하는 일을 한다.
  // 또는 하위 컴포넌트를 실행하는 일을 한다.


  _createClass(LikeButton, [{
    key: "render",
    value: function render() {
      var _this2 = this;

      if (this.state.liked) {
        return "좋아합니다!";
      }

      return React.createElement(
        "button",
        { onClick: function onClick() {
            return _this2.setState({ liked: true });
          } },
        "\uC88B\uC544\uC694"
      );
    }
  }]);

  return LikeButton;
}(React.Component);

document.querySelectorAll(".like-container").forEach(function (likeContainer) {
  var root = ReactDOM.createRoot(likeContainer);
  root.render(React.createElement(LikeButton));
});
console.log("종료됨!");