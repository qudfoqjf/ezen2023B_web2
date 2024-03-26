import { useState } from "react";

export default function ConfirmButton (props){
    //1. 상태(state)관리 변수
    const [isConfirmed, setIsConfirmed] =useState(false);
    //  useState: 리액트 훅 중 사용빈도가 높은 함수
        // useState(초기값);
        // 반환: 배열
            //[0]: 값이 저장된 변수
            //[1]: 값을 수정할 수 있는 set함수(* (주소값) 변경시 해당 컴포넌트 랜더링)
            // setIsConfirmed((매개변수)=>{})
                //* prevIsConfirmed

    //2. 함수
    function handleConfirm(){
        
        setIsConfirmed((prevIsConfirmed)=>!prevIsConfirmed);
        //console.log(prevIsConfirmed);
    }
    
    
    
    return(<>
        <button onClick={handleConfirm} disabled={isConfirmed}>
            {isConfirmed?'확인됨':'확인하기'}
        </button>
        </>)
}


/*
    기존방식            리액트 방식
    onclick=""         onClick={}
    handleConfirm()    handleConfirm 또는(event) =>  handleConfirm(매개변수1, 매개변수2, event)

*/