package kr.co.iei.contents.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiMovie {
	private String backdropPath; 
	private List<String> genreIds; 
	private String movieId;
	private String originCountry;
	private String title;
	private String originalTitle;
	private String releaseDate;
	private String posterPath;
	private String overview;
	private String runtime;
}
