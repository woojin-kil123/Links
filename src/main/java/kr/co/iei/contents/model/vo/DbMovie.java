package kr.co.iei.contents.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DbMovie {
	private String movieId;
	private String movieTitle;
	private String  movieAvgPoint;
	private String moviePlatform;
	private int linkClick;
	private String posterPath;
	
}
