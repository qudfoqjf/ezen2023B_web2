import axios from "axios";
import { useRef } from "react";

export default function Write(props){
    //1. 상태변수
    const inputBcontent = useRef(null);
    
    const onButtonClick=()=>{
        let bcontent =inputBcontent.current.value;
        

        const writeData= new FormData
        writeData.append('bcontent',bcontent);
        axios.post("/board/post.do",writeData)
        .then( response => { console.log( response ) // 2xx
        if( response.data){
            alert('글쓰기 성공');
            window.location.href = "/member/login"; // <a />
        }else{   alert('글쓰기 실패')   }
        
        }
    )}
    return(<>
    <form>
        <input  ref={inputBcontent} type="text" />
        <button type="button" onClick={onButtonClick}>작성</button>
    </form>
    </>)

}