package kr.co.iei.comment.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReComment {
	private int reCommentNo;
	private String memberId;
	private int commentNo;
	private String reCommentContent;
	private String reRegDate;	
	private int likeCount;
	private int isLike;
}
