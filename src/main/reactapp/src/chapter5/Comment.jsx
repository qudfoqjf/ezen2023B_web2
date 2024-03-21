//import styles from css파일경로;
import styles from './Comment.css';
//이미지파일 호출: import 이미지변수명 from '이미지파일경로';
import image from './image.jpg'

export default function Comment(props){
    return(
    <div class="wrapper">
        <div>
            <img src={image} class="image"/>
        </div>
        <div class="contentContainer">
            <span class="nameText">{props.name}</span>
            <span class="commentText">{props.comment}</span>
        </div>
    </div>
    )
}