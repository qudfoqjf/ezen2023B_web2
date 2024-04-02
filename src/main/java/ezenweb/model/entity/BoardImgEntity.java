package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "boardimg")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardImgEntity extends BaseTime{


    @Id
    private String bimg;    // 파일명 (중복없음-- 유저는 다수고 서버는 하나라서)

    @JoinColumn( name="bno_fk")
    @ManyToOne
    private BoardEntity boardEntity;
}
