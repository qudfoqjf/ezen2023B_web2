
/*
    컴포넌트 만드는 방법
        1. 첫글자를 대문자로하는 .jsx 파일을 생성한다.
        2. 함수형 컴포넌트 생성
            1. 컴포넌트 함수 선언

            funtion 컴포넌트명(props){
                return (JSX형식문법);
            }
            2. 다른 곳에서 해당 파일을 import시 반환할 컴포넌트 명시
             해당 파일에 여러가지의 함수가 존재 할 수 있으므로
             export default 해당파일호출시 반환 컴포넌트명;
        합체:
        export default funtion 컴포넌트명(props){
            return (JSX형식문법);
        }
    컴포넌트를 호출하는 방법
    1. 다른 컴포넌트에서 해당 컴포넌트 호출하는 방법
    import 컴포넌트명 from 컴포넌트파일경로

*/
export default function Book(props){
    return (
        <div>
            <h1>{props.name}</h1>
            <h2>이 사람의 나이는{props.age}세 입니다.</h2>
        </div>
    );
}



