export default function AttendanceBook(props){

    //1. 샘플 데이터
    const students=[
        {name: 'Inje'},{name:'Steve'},
        {name:'Bill'},{name:'jeff'}
    ]
    return(<>
        <ul>
            {
                //-------jsx시작

                students.map((student,index)=>{
                    return(<>
                    <li
                     key={student.id}
                     id={student.id}
                     className={student.id}
                     >
                        {students.name}
                        </li>
                        </>)
                })


            }


        </ul>
    
    
    </>)
}