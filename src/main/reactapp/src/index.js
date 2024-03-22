import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// 내가 만든 컴포넌트 import(가져오기) 호출
// import 컴포넌트명 from 컴포넌트 파일경로;
import JSX선언 from './chapter3/1_jsx선언';

// chapter3 실습
import Book from './chapter3/Book'
import Library from './chapter3/Library'

// chapter4 실습
import Clock from './chapter4/Clock'

// chapter5 실습
import CommentList from './chapter5/CommentList';

// chapter7 실습
import Counter from './chapter7/Counter'
import UseStateList from './chapter7/UseStateList';
import UseStateList2 from './chapter7/UseStateList2';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>    
    {/* <CommentList/> */}
    {/* <Counter/> */}
    <UseStateList/>
  </React.StrictMode>
)
// root.render(    //랜더링 되는 곳
//   <React.StrictMode>   유효성검사 해주는 모드 (테스트한번 실행해줌)
//     {/*<App /> */}
//     {/*<JSX선언 /> */}
//     {/*<Book /> */}
//     {/*<Library/>*/}
//     <Clock/>
//   </React.StrictMode>
// );

//1. setInterval(함수(),밀리초)
// setInterval(()=>{
//   root.render(<Clock/>);
// }, 1000)




// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
