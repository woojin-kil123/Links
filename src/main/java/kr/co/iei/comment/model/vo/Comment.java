package kr.co.iei.comment.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
	private int commentNo;
	private String memberId;
	private String contentNo;
	private String commentContent;
	private int readCount;
	private String regDate;
	private String contentTitle;
}
