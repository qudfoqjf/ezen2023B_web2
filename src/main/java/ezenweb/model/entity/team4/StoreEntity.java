/*
package ezenweb.model.entity.team4;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="store")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int sno;
    @Column(nullable = false, columnDefinition = "varchar(50)", unique = true)
    private String sname;
    @Column(columnDefinition = "varchar(13)")
    private String sphone;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg1;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg2;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg3;
    @Column(nullable = false, columnDefinition = "longtext")
    private String simg4;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String sadress ;
    @Column(nullable = false, columnDefinition = "text")
    private String scontent ;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int sstate;
    @Column(nullable = false, columnDefinition = "varchar(20)", unique = true)
    private String snumber;
    @Column(nullable = false)
    private int categorya;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int categoryb;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String slat;
    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String slng;
    @Column(nullable = false, columnDefinition = "char(8) default '12345678'")
    private String scode ;
    @Column(nullable = false, columnDefinition = "int unsigned")
    private int mno;
}
*/
