package kr.co.iei.news.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsFile {
	private int newsFileNo;
	private int newsNo;
	private String filename;
	private String filepath;
}
