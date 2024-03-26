import { useState } from "react"
import Toolbar from "./Toobar";

export default function LandingPage(props){

    //1. state 상태 변수
    const [isLoggedIn,setIsLoggedIn]= useState(false);

    //2. 로그인 클릭 함수
    const onClickLogin=()=>{
        setIsLoggedIn(true);
    }

    //3. 로그아웃 클릭함수
    const onClickLogout=()=>{
        setIsLoggedIn(false);
    }
    return(<>
        <div>
            <Toolbar
                isLoggedIn={isLoggedIn}
                onClickLogin={onClickLogin}
                onClickLogout={onClickLogout}
                setIsLoggedIn={setIsLoggedIn}
            />
            <div> 소플과 함께하는 리액트 공부!</div>
        </div>
    
    </>)
}