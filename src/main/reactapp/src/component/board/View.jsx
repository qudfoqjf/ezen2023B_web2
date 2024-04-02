import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";

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
        <div style={{display:"flex", flexWrap:"wrap"}}>
            {boardInfo.map((board)=>{
                return(
                    <MediaCard board = {board}/>
                )
            })
            }
        </div>
    </>
    );
}