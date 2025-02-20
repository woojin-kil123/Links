package kr.co.iei.contents.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentStar {
	private String contentNo;
	private String memberId;
	private int starpoint;
}
