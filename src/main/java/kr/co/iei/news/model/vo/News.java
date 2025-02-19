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
<<<<<<< HEAD
	private int newsNotice;
=======
>>>>>>> parent of f8e864b (2.18)
	private List<NewsFile> fileList;
}

