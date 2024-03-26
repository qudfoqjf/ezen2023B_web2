import { useState } from "react";

export default function NameForm(props){
    //1. 함수1
    function 제출1(){
        let nameInput = document.querySelector('#nameInput').ariaValueMax;
        console.log(nameInput);
    }

    //2. 
        //1. state변수
    const[value,setValue] = useState('');
        //2. state 변경함수 : e(event: 해당 이벤트(change)가 발생한 결과 정보 객체)
    const handleChange = (e)=>{
        console.log(e);console.log(e.target);console.log(e.target.value);
        setValue(e.target.value);}


    //3.
    const[value2,setValue2]= useState('');

    const handleChange2= (e)=>{
        setValue2(e.target.value);
        e.preventDefault(); //브라우저들의 이벤트들을 제거
    }
    
    //4. 
    const [value3,setvalue3]=useState('grape');
    const handleChange3= (e)=>{
        setvalue3(e.target.value);
        e.preventDefault();
    }



    //제출함수
    const handleSubmit = (e)=>{
        console.log(value);
        console.log(value2);
        console.log(value3);
    }
    return(<>
        <form>
            이름:<input id="nameInput" type="text"/>
            <button type="button" onClick={제출1}>제출1</button>
        </form>
        <form onSubmit={handleSubmit}>
            이름: <input type="text" value={value} onChange={handleChange}/><br/>
            요청사항: <textarea value={value2} onChange={handleChange2}></textarea>
            <button type="button" onClick={handleSubmit}>제출2</button>
            과일을 선택하세요.
            <select value={value3} onChange={handleChange3}>
                <option value="apple">사과</option>
                <option value="banana">바나나</option>
                <option value="grape">포도</option>
                <option value="watermelon">수박</option>
            </select>
        </form>
    </>)
}