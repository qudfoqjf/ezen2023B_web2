package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // *요청 근원지를 교차 허용
public class MemberController {
    @Autowired private MemberService memberService;
    @PostMapping("/signup/post.do") //1. 회원가입
    public int doSignupPost( @RequestBody MemberDto memberDto){
        if(memberService.doDuplicate(memberDto.getMemail())){
            if(memberService.doSignupPost(memberDto)){
                return 3;// 회원가입 성공
            }
            return  2;  //회원가입 실패
        }
        return  1; //이메일 중복
    }

    @PostMapping("/login/post.do")   //2. 로그인
    public boolean doLoginPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doLoginPost(memberDto);
    }


    @GetMapping("/logout/get.do")    //3. 로그아웃
    public boolean doLogOutGet(){
        return memberService.doLogOutGet();
    }

    @GetMapping("/login/info/get.do")   //4. 회원정보
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }

    @GetMapping("/find/email/get.do")
    public boolean doFindEmail(String memail){
        return  memberService.getFindMemail(memail);
    }

    @GetMapping("/find/myboard/get.do")
    public List<BoardDto> findByMyBoardList(){
        memberService.findByMyBoardList();
        return memberService.findByMyBoardList();
    }

 /*   //5. 아이디 중복검사
    @PostMapping("/signup/duplicate.do")
    public boolean doDuplicate(@RequestBody String mid){
        System.out.println("mid = " + mid);
        return memberService.doDuplicate(mid);
    }*/











}





































































