package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class BoardService {

    //* 리포지토리 객체
    @Autowired
     private BoardEntityRepository boardEntityRepository;
    @Autowired
     private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    @Transactional
    public boolean postBoard(){
        //============테스트=============
        //1. 회원가입
            //1. 엔티티 객체 생성
        MemberEntity memberEntity= MemberEntity.builder()
                .memail("qwe@qwe.com").mpassword("1234").mname("유재석")
                .build();
            //2. 해당 엔티티를 db에 저장할 수 있도록 조작
       MemberEntity saveMemberEntity= memberEntityRepository.save(memberEntity);
        //2. 회원가입된 회원으로 글쓰기
            //1.
        BoardEntity boardEntity=BoardEntity.builder()
                .bcontent("게시물글입니다.")
                .build();
            //2. 글쓴이
        boardEntity.setMemberEntity((saveMemberEntity));
            //3.
        BoardEntity saveboardEntity=boardEntityRepository.save(boardEntity);

        //3. 해당 글에 댓글 작성
            //1.
        ReplyEntity replyEntity= ReplyEntity.builder()
                .rcontent("댓글입니다")
                .build();
            //2-1.
        replyEntity.setMemberEntity(saveMemberEntity);
            //2-2
        replyEntity.setBoardEntity(saveboardEntity);
            //3.
        replyEntityRepository.save(replyEntity);

        return false;
    }
    @Transactional
    public List<Object> getBoard(){
        //1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result=boardEntityRepository.findAll();
        System.out.println(result);
        System.out.println("result = " + result);
        System.out.println("작성자 : " + result.get(0).getMemberEntity().getMemail() );

        return null;
    }
    @Transactional
    public boolean putBoard(){

        BoardEntity boardEntity= boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);

        return false;
    }
}
