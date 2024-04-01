import axios from "axios";
import { useEffect, useState } from "react";

export default function View(props){
    //1. 상태변수
    const [boardInfo,setBoardInfo]= useState([]);

    useEffect(()=>{
    axios.get("/board/get.do")
    .then(response=>{console.log(response.data)
        setBoardInfo(response.data);
    })
    .catch(error=>{console.log(error);})
    },[]);
    console.log(boardInfo);
    console.log(boardInfo.bcontent);
    
    return(<>
        <div>
            {boardInfo.map((b)=>{
                return(<>
                <div>{b.bcontent}</div>
                <div>{b.memail}</div>
                </>)
            })}

        </div>
    </>
    );
}