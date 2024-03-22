
import { useState } from "react";

export default function UseStateList(props){

    //2. point 상태관리변수
        //1. input 입력된 값을 저장하는 상태관리변수
    let [pointInput, setPointInput]= useState('');
        //2. input 입력된 값들을 저장하는 리스트 상태관리변수
    let [pointList, setPointList]= useState([]);

    //1. 등록 버튼 클릭시.
    function 등록(){console.log('등록()');
    //1.
    //========== 리액트 방식==========//
        pointList.push(pointInput);
        setPointList([...pointList]);    //재렌더링 X
    //

    
    }; //f end
    //3.
        function 입력변경(e){
            setPointInput(e.target.value);
        }

        return(<>
            <div>
                <div>
                    <input type="text" value={pointInput}  onChange={입력변경}/>
                    <button type="button" onClick={등록}>등록</button>
                </div>
                <div>
                    { 
                        pointList.map((point)=>{
                            return (<div>{point}</div>)
                        })
                    }
                    
                </div>
                
            </div>
        </>);
}