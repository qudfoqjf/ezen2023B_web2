import { Axios } from "axios";
import { useRef, useState } from "react";

export default function Chatting(props){

    //1. 해당 컴포넌트가 렌더링 될 때 소켓은 재랜더링 방지 useRef
        // useRef(초기값):   {current: 값}
        // -컴포넌트가 렝더링시 참조값을 고정할 수 있다. 
    let clientSocket= useRef(null);
    //2. Ref 참조가 없으면
    if(!clientSocket.current){

    //======(클라이언트)웹소켓 구현=================
        //1. new WebSocket(서버소켓url)
    clientSocket.current = new WebSocket("ws://192.168.17.34:80/chat");
            //확인
            console.log(clientSocket);
    // onclose// onerror// onmessage// onopen : WebSocket 객체내 포함         
        // 2. 각 메소드 정의
                //- 1. 클라이언트소켓이 close가 발생했을떄 콜백함수 정의
    clientSocket.current.onclose= (e)=>{console.log(e);}
                //- 2. 클라이언트 소켓이 error가 발생했을떄 콜백함수 정의(*error는 이미 발생 ,다음 
    clientSocket.current.onerror= (e)=>{console.log(e);}    
                //- 3. 클라이언트 소켓이 message 받았을때 콜백함수 정의
    clientSocket.current.onmessage = (e)=>{console.log(e);
        msgList.push(e.data);
        setMsgList([...msgList]);
        
       }
    }
             //- 4. 클라이언트 소켓이 open 발생했을때 콜백함수 정의
    clientSocket.onopen = (e)=>{console.log(e);}

    const onSend= ()=>{
            //3. 서버소켓으로 부터 메시지 보내기
            clientSocket.current.send(msgInput);
    }
        
 
        //4. 연결종료
    // clientSocket.close();
    //==================================================
    // -채팅 내용 입력창
    const [msgInput,setMsgInput]= useState('');
    // -채팅창의 내용물들
    const [msgList,setMsgList]=useState([]);


    return(<>
        <div>
            <h3>채팅방</h3>
            <div>
                {
                    msgList.map((msg)=>{
                        return <div>{msg}</div>
                    })
                }
            </div>
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}} ></textarea>  
            <button type="button" onClick={onSend}>전송</button>
        </div>

    
    </>)



}