package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    @Autowired MemberEntityRepository memberEntityRepository;

    // - ( 시큐리티 ) 로그인 서비스 커스텀 ( implements UserDetailsService )
    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {
        // 1. 로그인창에서 입력받은 아이디
        System.out.println("memail = " + memail);
        // 2. 입력받은 아이디로  실제 아이디와 실제 (암호화된)패스워드 // memail 이용한 회원엔티티 찾기
        MemberEntity memberEntity = memberEntityRepository.findByMemail( memail );

        // - ROLE 부여
        if(memberEntity ==null){return null;}

        List<GrantedAuthority> 등급목록 = new ArrayList<>();
        등급목록.add( new SimpleGrantedAuthority("ROLE_"+memberEntity.getMrol())); // ROLE_등급명

        // 3.  UserDetails 반환 [ 1.실제 아이디 2. 실제 패스워드 ]
            // UserDetails 목적 : Token에 입력받은 아이디/패스워드 검증하기위한 실제 정보 반환.
        UserDetails userDetails = User.builder()
                .username( memberEntity.getMemail() )   // 실제 아이디
                .password( memberEntity.getMpassword() ) // 실제 비밀번호(암호화)
                .authorities( 등급목록 ) // ROLE 등급
                .build();

        return userDetails;
    }

    // 1. 회원가입 ( 시큐리티 사용시 패스워드 암호화 필수 )
    public boolean doSignupPost(  MemberDto memberDto){ System.out.println("memberDto = " + memberDto);
        // --  Dao 아닌 엔티티 이용한 레코드 저장하는 방법
        // 1. 엔티티를 만든다.
        // 2. 리포지토리 통한 엔티티를 저장한다. ( 엔티티 저장 성공시 매핑된 엔티티 반환 )
        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity()); // insert
        // 3. 엔티티 생성이 되었는지 아닌지 확인 ( PK )
        if( savedEntity.getMno() > 0 ) return true;
        return false;
    }

    // * 로그인 했다는 증거/기록
    @Autowired private HttpServletRequest request;

    // 2. 로그인( 세션 저장 )
    public boolean doLoginPost( MemberDto memberDto ){

        // 1.
//        MemberEntity result1 = memberEntityRepository.findByMemailAndMpassword(
//                memberDto.getMemail() , memberDto.getMpassword() );
//        System.out.println("result1 = " + result1);

        // 2.
//        boolean result2 = memberEntityRepository.existsByMemailAndMpassword(
//                memberDto.getMemail() , memberDto.getMpassword() );
//        System.out.println("result2 = " + result2);

        // 3.
        MemberEntity result3 = memberEntityRepository.findByLoginSQL(
                memberDto.getMemail() , memberDto.getMpassword() );
        System.out.println("result3 = " + result3);

        if( result3 == null )return false; // 로그인 실패
        // 세션부여
        request.getSession().setAttribute("loginInfo" , result3.toDto() ); // *회원번호( 1 ) , 시큐리티 ( 권한 )
        return true;

    } // f end

    // 3. 로그아웃( 세션 삭제 )
    public boolean doLogOutGet( ){
        request.getSession().setAttribute("loginInfo" , null );
        return true;
    }
    // 4. 현재 로그인된 회원정보 호출 ( 세션 값 반환/호출 )
    public MemberDto doLoginInfo(){
        // ( 시큐리티를 사용하기전 )
//        Object object = request.getSession().getAttribute("loginInfo");
//        if( object != null ){
//            return (MemberDto)object; // 강제형변환
//        }
//        return null;
        // 1. ( 시큐리티를 사용했을떄 ) Principal : 본인/주역/주체자 : 브라우저마다 1개
        Object object = SecurityContextHolder.getContext() .getAuthentication().getPrincipal();
        System.out.println("object = " + object);
        // 2. 만약에 로그인 상태가 아니면
        if( object.equals("anonymousUser" ) ){ return  null; } // anonymous : 익명 <--> 비로그인
        // 3. 로그인 상태이면 UserDetails 타입 변환
        UserDetails userDetails = (UserDetails)object;
        // 4. 로그인 성공한 엔티티 찾기
        MemberEntity m = memberEntityRepository.findByMemail( userDetails.getUsername() );
        // 5. 회원정보( 비밀번호 제외 권장 ) 반환 ( 주로 다른 서비스나 리액트 사용중 )
        return MemberDto.builder()
                .memail(m.getMemail()) .mname( m.getMname() ).mno( m.getMno() )
                .build();
    }

    // 5. 아이디/이메일 중복검사
    public boolean getFindMemail( String memail ){
        // 1. 모든 엔티티에서 해당 필드의 값을 찾는다.
//        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
//        for( int i = 0 ; i<memberEntityList.size() ; i++ ){
//            MemberEntity memberEntity = memberEntityList.get(i);
//            if( memberEntity.getMemail().equals( memail ) ){
//                System.out.println("memberEntity = " + memberEntity);
//            }
//        }
//        // 2.특정 필드의 조건으로 레코드/엔티티 검색
//        MemberEntity result1 = memberEntityRepository.findByMemail( memail );
//        System.out.println("result1 = " + result1);
//
        // 3. 특정 필드의 조건으로 존재여부 검색
        boolean result2 =  memberEntityRepository.existsByMemail( memail );
        System.out.println("result2 = " + result2);
//        // 4. 직접 native SQL 지원
//        MemberEntity result3 = memberEntityRepository.findByMemailSQL( memail );
//        System.out.println("result3 = " + result3);
        return result2;
    }

    // 6. (로그인) 내가쓴글
    // public List< Map<Object,Object> > findByMyBoardList(){
    public List<BoardDto> findByMyBoardList(){
        // 1. 세션에서 로그인된 회원번호 찾는다.
        MemberDto loginDto = doLoginInfo();
        // 2. 확인
        if( loginDto == null )return null;
        // =========== 1.양방향일때 ======== //
            // 1. 로그인된 회원번호를 이용한 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity
                = memberEntityRepository.findById( loginDto.getMno() );
            // 2. 만약에 엔티티가 존재하면
        if( optionalMemberEntity.isPresent() ){ // findById의 결과에 엔티티 존재하면
            // 3. Optional 에서 엔티티 꺼내기
            MemberEntity memberEntity = optionalMemberEntity.get();
            // 4. 내가 쓴글
            List<BoardEntity> result1 = memberEntity.getBoardEntityList();
                System.out.println("result1 = " + result1);
            // 내가 쓴글 엔티티 리스트를 ---> 내가 쓴글 DTO 리스트로 변환
            List<BoardDto> boardDtoList = new ArrayList<>();
            result1.forEach( (entity)->{
                boardDtoList.add( entity.toDto() );
            });
            return boardDtoList;
        }else{     return null;   }
        // ========== 2. 단방향일떄 ======== //
//        List< Map<Object,Object> > result2
//                = memberEntityRepository.findByMyBoardSQL( loginDto.getMno() );
//        return result2;

    }
}
/*
    Optional 클래스
        - 해당 객체가 null 일수도 있고 아닐수 있다.
        - 검색결과가 없을경우 null 반환될때 패키징
        - 메소드
 */









