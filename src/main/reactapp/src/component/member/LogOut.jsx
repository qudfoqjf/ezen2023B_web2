import axios from "axios";

export default function LogOut(props){

    axios.get("/member/logout/get.do")
    .then( response => { console.log( response )
        if(response){
            alert('로그아웃 성공');
            window.location.href="/";
        }else{alert('로그아웃 실패')}

    })
    .catch(error=> {console.log(error);})

}