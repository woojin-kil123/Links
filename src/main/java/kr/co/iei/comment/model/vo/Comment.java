package kr.co.iei.comment.model.vo;

import java.util.List;

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
	private String posterPath;
	private String movieTitle;
	private int likeCount;
	private int isLike;
	private int reCommCount;
	private int starPoint;
	private List<ReComment> listNo;
}
