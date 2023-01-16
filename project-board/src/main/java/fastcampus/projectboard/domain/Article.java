package fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //MySQL은 IDENTITY로 설정
    private Long id;
    @Column(nullable = false) @Setter private String title;
    @Column(nullable = false, length = 10000) @Setter private String content;
    @Setter private String hashtag;
    @OrderBy("id")
    @ToString.Exclude
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    protected Article() {}  //모든 JPA 엔티티는 기본생성자가 있어야 한다

    // 생성자는 private 으로 막아두고 of 메서드에서 사용한다
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    //Equals And Hashcode  (alt + ins 누르고 드롭다운에서 object 선택하기)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
