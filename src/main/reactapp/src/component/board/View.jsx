import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function View(props){
    //1. 상태변수
    const [pageDto,setPageDto]= useState({
        page:1,count:0, data:[]
    });

    useEffect(()=>{
        const info={page:pageDto.page, view:4}

    axios.get("/board/get.do",{params:info})
    .then(response=>{console.log(response.data)
        setPageDto(response.data);
    })
    .catch(error=>{console.log(error);})
    },[pageDto.page]);

    console.log(pageDto);
   
    
    const handleChange = (e, value) => {
        pageDto.page=value;
        setPageDto({...pageDto});
      };
    


    return(<>
        <div style={{display:"flex", flexWrap:"wrap"}}>
            {pageDto.data.map((board)=>{
                return(
                    <MediaCard board = {board}/>
                )
            })
            }
        </div>
        <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />
    </>
    );
}

// count: The total number of pages.
// page : The current page.
// onChange : Callback fired when the page is changed.