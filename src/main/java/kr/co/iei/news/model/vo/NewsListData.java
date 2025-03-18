package kr.co.iei.news.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsListData {
    private List<News> importantNewsList; // 중요 공지 리스트
    private List<News> pagedNewsList; // 페이지네이션 적용된 일반 뉴스 리스트
    private String pageNavi; // 페이지네이션 HTML
}
