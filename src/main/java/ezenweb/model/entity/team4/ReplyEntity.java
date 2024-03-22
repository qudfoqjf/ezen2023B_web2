package ezenweb.model.entity.team4;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reply")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rpno; //int auto_increment primary key         # 댓글번호
    @Column(nullable = false, length = 100)
    private String rpcontent; //    varchar(100) not null,   # 댓글내용
    @Column(columnDefinition = "datetime default now()")
    private String rpdate;  //default now(),      # 댓글 최초등록일
    @Column(columnDefinition = "int unsigned")
    private int mno; //int unsigned,               # 댓글작성자 FK
    @Column(columnDefinition = "int unsigned")
    private int bno; // int unsigned,               # 게시글 식별번호 FK
    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int rpindex; // unsigned default 0 not null,
}
