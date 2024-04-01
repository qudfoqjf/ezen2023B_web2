//1.
export default function ContextApp (props){
    
    return(<>
        <Toolbar theme = "dark"/>
    </>)
}
//2. 
function Toolbar (props){
    console.log(props); //props = {theme: 'dark'}
    return(<>
        <ThemedButton theme = {props.theme}/>
    </>)
}
//3.

function ThemedButton (props){
    console.log(props)  //props = {theme:'dark'}
    return(<>
        <Button theme={props.theme}/>
    </>)
}
