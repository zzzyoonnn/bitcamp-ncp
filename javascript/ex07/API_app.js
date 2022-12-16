// express 라이브러리 로딩하기
const express = require('express');

// HTTP 요청을 다루는 라이브러리 로딩하기
const request = require('request');


const port = 3000;  // 웹서버 포트 번호

// express()를 호출하여 웹서버를 준비한다.
const app = express();

// POST 요청으로 보낸 payload 데이터를 분석할 객체를 지정하기
// => Content-Type: application/x-www-form-urlencoded 형식으로 된 payload 처리
//    예) name-hong&ade=20
// let options = new Object();
// options.extended = true;

app.use(express.urlencoded({extended: true}));




// 04-3
app.get( '/exam04-3', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*')   // CORS 문제 해결
  res.set('Content-Type', 'text/html; charset=UTF-8');

  let arr = [
    {no: 1, title: '제목1', writer: '홍길동', viewCnt: 19},
    {no: 2, title: '제목2', writer: '임꺽정', viewCnt: 312},
    {no: 3, title: '제목3', writer: '유관순', viewCnt: 31},
    {no: 4, title: '제목4', writer: '안중근', viewCnt: 100},
    {no: 5, title: '제목5', writer: '윤봉길', viewCnt: 200}
  ];

  // 배열 객체를 JSON 문자열로 변환하여 클라이언트에게 보낸다.
  // => serialization(직렬화)
  res.send(JSON.stringify(arr));
});


// 기상청 과제
app.get('/proxy2', (req, res) => {

  res.set('Access-Control-Allow-Origin', '*')   // CORS 문제 해결
  res.set('Content-Type', 'application/json; charset=UTF-8');

  let openApiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" + 
  "serviceKey=yfdN%2F38PW9LPitcFMM1eLkEWmcRcdefgFaPWR3ychTGoJ5UItI55b2juwpNYYrH%2FbihkhwwD8BuFOW4G%2Bd3Ixg%3D%3D" +
  "&pageNo=1" +
  "&numOfRows=1000" +
  "&dataType=JSON" +
  "&base_date=" + req.query.base_date +
  "&base_time=0600" +
  "&nx=" + req.query.nx +
  "&ny=" + req.query.ny;

  console.log(openApiUrl);

  request.get({
      uri: openApiUrl
    }, (error, response, body) => {
      // console.log(body);
      // console.log(error);
      res.send(body);
  });
});

// 웹서버 실행하기
app.listen(
  3000, // 포트 번호 지정
  () => {   // 서버가 시자되었을 때 호출될 함수 = 리스너 = 이벤트 핸들러
    console.log(`${port}번 포트에서 서버 시작했음!`);
  } 
)

