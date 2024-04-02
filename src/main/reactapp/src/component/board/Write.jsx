import axios from "axios";
import { useRef } from "react";

export default function Write(props){
    //1. 상태변수
    const inputBcontent = useRef(null);
    const inputFile = useRef(null);
    const onButtonClick=()=>{
        // let bcontent =inputBcontent.current.value;
        // let uploadList = inputFile.current.value;

        // const writeData= new FormData();
        // writeData.append('bcontent',bcontent);
        // writeData.append('uploadList',uploadList);
        // console.log(writeData);
        //writeData.append('uploadList',uploadList);
        axios.post("/board/post.do",inputBcontent.current)
        .then( response => { console.log( response ) // 2xx
        if( response.data==1){
            alert('로그인을 해주세요');
            window.location.href = "/member/login"; // <a />
        }
        else if( response.data==2){   alert('이미지 등록실패')   }
        else if( response.data==3){   alert('글쓰기 성공')   }
        else if( response.data==4){   alert('글쓰기 실패')   }
        }
    )}
    return(<>
    <form ref={inputBcontent} >
        <input   type="text" />
        <input type="file" name="uploadList" multiple accept="image/*"/>
        <button type="button" onClick={onButtonClick}>작성</button>
    </form>
    </>)

}