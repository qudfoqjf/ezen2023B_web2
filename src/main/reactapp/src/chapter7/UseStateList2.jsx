import { useState } from "react";

export default function UseStateList2(props){

    //2. point 상태관리변수
    let [point, setPoint]= useState('');


    //1. 등록 버튼 클릭시.
    function 등록(e){console.log('등록');
    //========== 고전 방식==========//
    //1.
    let value = document.querySelector('.pointInput').value;
    //2. 
    console.log(value);
    

    }; //f end
    //3.
        function 입력변경(e){
            setPoint(e.target.value);
        }

        return(<>
            <div>
                <div>
                    <input value={point}
                    className="pointInput"
                    type="text" 
                    onChange={입력변경}/>
                    <button type="button" onClick={등록}>등록</button>
                </div>
                <div>
                    <div>{point}</div>
                </div>
            </div>
        </>);
}
