package datastreams_knu.bigpicture.news.domain;

import datastreams_knu.bigpicture.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class News extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    @Lob
    private String content;

    @OneToMany(mappedBy = "news", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsInfo> newsInfos = new ArrayList<>();

    public void setNewsInfos(List<NewsInfo> infos) {
        newsInfos.clear();
        for (NewsInfo info : infos) {
            info.setNews(this);
            newsInfos.add(info);
        }
    }

    @Builder
    public News(String keyword, String content) {
        this.keyword = keyword;
        this.content = content;
    }

    public static News of(String keyword, String content) {
        return News.builder()
            .keyword(keyword)
            .content(content)
            .build();
    }

    public static News of(String keyword) {
        return News.of(keyword, null);
    }

    public void setContent(String content) {
        this.content = content;
    }
}
