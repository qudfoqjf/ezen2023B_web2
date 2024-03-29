package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired MemberEntityRepository memberEntityRepository;
    @Autowired private HttpServletRequest request;
    //1. 회원가입
    public boolean doSignupPost(  MemberDto memberDto){ System.out.println("memberDto = " + memberDto);
        // --  Dao 아닌 엔티티 이용한 레코드 저장하는 방법
        // 1. 엔티티를 만든다.
        // 2. 리포지토리 통한 엔티티를 저장한다. ( 엔티티 저장 성공시 매핑된 엔티티 반환 )
        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity()); // insert
        // 3. 엔티티 생성이 되었는지 아닌지 확인 ( PK )
        if( savedEntity.getMno() > 0 ) return true;
        return false;
    }

    //2. 로그인
    public boolean doLoginPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
       /* //1.
        MemberEntity result1= memberEntityRepository.findByLoginSQL(
            memberDto.getMemail(), memberDto.getMpassword());
        System.out.println("result1 = " + result1);
        //2.
        boolean result2=memberEntityRepository.existsByMemailAndMpassword(
                memberDto.getMemail(), memberDto.getMpassword()
        );
        System.out.println("result2 = " + result2);*/
        //3.
        MemberEntity result3= memberEntityRepository.findByLoginSQL(
                memberDto.getMemail(), memberDto.getMpassword()
        );
        System.out.println("result3 = " + result3);
        if(result3==null) return false; //로그인 실패

        //세션부여
        request.getSession().setAttribute("loginInfo",result3.toDto());
        return true;
    }
    //3. 로그아웃(세션삭제)
    public boolean doLogOutGet(){
        request.getSession().setAttribute("loginInfo",null);
        return true;
    }

    //4. 현재 로그인 회원정보 호출(세션 값 반환/호출)
    public MemberDto doLoginInfo(){
        Object object =request.getSession().getAttribute("loginInfo");
        if(object !=null){
            return (MemberDto)object;   //강제 형변환
        }
        return null;
    }

    //5. 아이디 중복검사
    public boolean doDuplicate(String mid) {
        //1. 리포지토리를 통한 모든 회원 엔티티 호출
        List<MemberEntity> memberEntityList =
                memberEntityRepository.findAll();
        for (int i = 0; i < memberEntityList.size(); i++) {
            MemberEntity m = memberEntityList.get(i);
            //3. 만약에 아이디가 동일하면 (엔티티와 dto)
            if (m.getMemail().equals(mid)) {
                return false;
            }

        }   return true;
    }



    //5. 아이디(이메일) 중복검사
    public boolean getFindMemail(String memail){

       /* //1. 모든 엔티티에서 해당 필드의 값을 찾는다.
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for(int i= 0;i<memberEntityList.size();i++){
            MemberEntity memberEntity = memberEntityList.get(i);
            if(memberEntity.getMemail().equals(memail)){
                System.out.println("memberEntity = " + memberEntity);
            }
        }
        //2. 리포지토리 추상메소드 이용하는 방법
        MemberEntity result1=memberEntityRepository.findByMemail(memail);
        System.out.println("result1 = " + result1);*/
        //3. 특정 필드의 조건으로 존재여부 검색
        boolean result2 = memberEntityRepository.existsByMemail(memail);
        System.out.println("result2 = " + result2);
        /*//4. 직접 native SQl 지원
        MemberEntity result3=memberEntityRepository.findByMemailSQL(memail);
        System.out.println("result3 = " + result3);
        return false;*/
        return result2;
    }


    //6. (로그인) 내가 쓴글
    public List<BoardDto> findByMyBoardList(){
        //1. 세션에서 로그인된 회원번호를 찾는다.
        MemberDto loginDto=doLoginInfo();
        //2. 확인
        if(loginDto==null) return null;

        //==============1. 양방향일때===========//
        // 1. 로그인된 회원번호를 이용한 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity
                = memberEntityRepository.findById((loginDto.getMno()));
        //2. 만약에 엔티티가 존재하면
        if(optionalMemberEntity.isPresent()){
            //3. Optional 에서 엔티티 꺼내기
            MemberEntity memberEntity = optionalMemberEntity.get();
            //4. 내가 쓴 글
            List<BoardEntity> result1 = memberEntity.getBoardEntityList();
            System.out.println("result1 = " + result1);
            // 내가 쓴 글 엔티티 리스트를 ---> 내가 쓴글 DTO 리스트로 변환
            List<BoardDto> boardDtoList = new ArrayList<>();
            result1.forEach((entity)->{
                boardDtoList.add(entity.toDto());
            });
            System.out.println("boardDtoList = " + boardDtoList);
            return boardDtoList;
        }else{return null;}
        //==========2. 단방향일때=============//
        /*List<Map<Object,Object>> result2
                = memberEntityRepository.findByMyBoardSQL(loginDto.getMno());
        return result2;*/

    }
}