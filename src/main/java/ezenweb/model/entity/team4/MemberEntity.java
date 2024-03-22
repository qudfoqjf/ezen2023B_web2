/*
package ezenweb.model.entity.team4;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int mno;
    @Column(nullable = false,unique = true,length = 12)
    private String mid;
    @Column(nullable = false,length = 16)
    private String mpw;
    @Column(nullable = false,length = 20)
    private String mname;
    @Column(nullable = false,unique = true,length = 30)
    private String memail;
    @Column(nullable = false,unique = true,length = 14)
    private String mphone;
    @Column(nullable = false,length = 8)
    private String mbirth;
    @Column(nullable = false,length = 2)
    private String msex;
    @Column(nullable = false,length = 30)
    private String maddress;

    // private LocalDateTime mdate;
    @Column(columnDefinition = "datetime default now()")
    private String mdate;


    @Column(columnDefinition = "varchar(255) default 'default.jpg'")
    private String mimg;
    @Column(columnDefinition = "int default 0")
    private int mstate;
}
*/
