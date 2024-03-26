import { useState } from "react";

export default function SignUp(props){
    //1. state 변수
    const [name,setName]= useState("");
    const [gender,setGender]= useState("남자");

    //2. state 변경함수
    const handleChangeName = (event)=>{
        setName(event.target.value);
    };
    const handleChangeGender=(event)=>{
        setGender(event.target.value);
    };


    //3. 제출함수
    const handleSubmit= (event)=>{
        alert(`이름:${name}, 성별:${gender}`);
        event.preventDefault();
    };

    return(<>
        <form onSubmit={handleSubmit}>
            <label>
                이름:
                <input type="text" value={name} onChange={handleChangeName}/>
            </label><br/>
            <label>
                성별:
                <select value={gender} onChange={handleChangeGender}>
                    <option value="남자">남자</option>
                    <option value="여자">여자</option>
                </select>
            </label>
            <button type="submit">제출</button>
        </form>
    </>)
}