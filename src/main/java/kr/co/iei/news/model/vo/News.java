package kr.co.iei.news.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
	private int newsNo;
	private String newsTitle;
	private String memberId;
	private String newsContent;
	private int newsReadCount;
	private String newsRegDate;
	private List<NewsFile> fileList;
}

