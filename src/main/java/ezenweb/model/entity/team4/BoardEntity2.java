/*
package ezenweb.model.entity.team4;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="boarda")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity2 {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno; //unsigned auto_increment primary key,   # 게시글 식별번호PK

    @Column(nullable = false , columnDefinition = "varchar(20)")
    private String bname;// varchar(20) not null,                  # 게시글 제목

    @Column(columnDefinition = "longtext")
    private String bcontent;// longtext,                        # 게시글 내용(사진,글가능)

    @Column(columnDefinition = "int default 0")
    private int bcount; // int default 0,          # 게시글 조회수

    @Column(columnDefinition = "datetime default now()")
    private String bdate; // datetime default now(),    # 게시글 최초등록날짜

    @Column(columnDefinition = "int unsigned")
    private int mno;//   int unsigned    # 등록한 사람 회원번호

    //@Column(nullable = true)
    private int categorya; // int not null,      # 카테고리 지역 (인덱스번호로 식별함)
    @Column(columnDefinition = "int default 0")
    private int categoryb;// int default 0

}
*/
