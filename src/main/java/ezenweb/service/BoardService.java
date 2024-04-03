package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.BoardImgEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.BoardImgEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    //* 리포지토리 객체
    @Autowired
     private BoardEntityRepository boardEntityRepository;
    @Autowired
     private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;
    @Autowired
    BoardImgEntityRepository boardImgEntityRepository;
    @Transactional
    public int postBoard(BoardDto boardDto){//============테스트=============
       MemberDto loginDto= memberService.doLoginInfo();
       if(loginDto == null)return 1; // 1. 로그인 필요

        //1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity=
        memberEntityRepository.findById(loginDto.getMno());
        //2. 찾은 엔티티가 존재하지 않으면 실패;
        if(!optionalMemberEntity.isPresent())return 1; //2. 회원정보 없음
        //3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();

        //. 글쓰기
        BoardEntity saveBoard=boardEntityRepository.save(boardDto.toEntity());
        // fk대입
        if(saveBoard.getBno()>=1){
            saveBoard.setMemberEntity(memberEntity);
            //4. 파일 업로드
            String filename="";
            for(int i=0; i<boardDto.getUploadList().size();i++) {
                filename = fileService.fileUpload((boardDto.getUploadList().get(i)));
                System.out.println("filename = " + filename);
                BoardImgEntity boardImgEntity=BoardImgEntity.builder()
                        .bimg(filename)
                        .build();
                BoardImgEntity saveBoardImgEntity= boardImgEntityRepository.save(boardImgEntity);
                saveBoardImgEntity.setBoardEntity(saveBoard);
                if(filename==null){return 2;}
            }
            return 3;       //글쓰기성공
        }
        return 4; // 글등록 실패
    }
    @Transactional
    public PageDto getBoard(int page,int view){
/*        //==============================1====================================//
        //1. 리포지토리를 이용한 모든 엔티티를 호출(테이블에 매핑하기전 엔티티)를 호출
        List<BoardEntity> result=boardEntityRepository.findAll();
        //2. Entity ---> Dto를 순회한다.
        List<BoardDto> boardDtoList= new ArrayList<>();
            //1. 꺼내온 entity를 순회한다.
        for(int i=0; i<result.size(); i++){
                //2. 하나씩 entity를 꺼낸다
            BoardEntity boardEntity = result.get(i);
                //3. 해당 엔티티를 dto로 변환한다.
            BoardDto boardDto= boardEntity.toDto();
                //4. 변환된 dto를 리스트에 담는다.
            boardDtoList.add(boardDto);
        }
        return boardDtoList;*/
        //============================================================//
        Pageable pageable = PageRequest.of(page-1,4);

        Page<BoardEntity> boardEntityPage=boardEntityRepository.findAll(pageable);

        System.out.println("boardEntityPage.getTotalPages() = " + boardEntityPage.getTotalPages());

        int count = boardEntityPage.getTotalPages();
        List<BoardDto> data= boardEntityPage.stream().map((boardEntity) -> {
            return boardEntity.toDto();
        }).collect(Collectors.toList());

        //2. 페이지 DTO 반환 타입 값 구성
            //1.
        PageDto pageDto=PageDto.builder()
                .data(data)
                .page(page)
                .count(count)
                .build();

        return pageDto;
        //====================page============================================

    }


    @Transactional
    public boolean putBoard(){

        BoardEntity boardEntity= boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    @Transactional
    public boolean deleteBoard(int bno){
        MemberDto memberDto= memberService.doLoginInfo();
        if(memberDto==null)return false;
        //2.내 게시물 롹인
        Optional<BoardEntity> optionalBoardEntity= boardEntityRepository.findById(bno);
        if(optionalBoardEntity.isPresent()){
            if (optionalBoardEntity.get().getMemberEntity().getMno()==memberDto.getMno()){
                boardEntityRepository.deleteById(bno);return true;
            }
        }

        return false;
    }
}
