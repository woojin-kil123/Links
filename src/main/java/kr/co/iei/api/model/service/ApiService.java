package kr.co.iei.api.model.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.iei.api.model.dao.ApiDao;
import kr.co.iei.contents.model.vo.ApiMovie;

@Service
public class ApiService {
	private String url = "https://api.themoviedb.org/3/movie/";
	@Value("${tmdb.api.key}")
	private String apiKey;

	@Autowired
	private ApiDao apiDao;
	
	private List movieList(String result) {
		List movieList = new ArrayList<ApiMovie>();
		
		JsonObject object = (JsonObject)JsonParser.parseString(result);
	 	JsonArray results = object.get("results").getAsJsonArray();
	 	for(int i=0; i<results.size(); i++){
	 		ApiMovie movie = new ApiMovie();
	 		JsonObject movieInfo = results.get(i).getAsJsonObject();
	 		//movie.setBackdropPath(movieInfo.get("backdrop_path").getAsString());
	 		
	 		JsonArray genres = movieInfo.get("genre_ids").getAsJsonArray();
	 		List genresList = new ArrayList<String>();
	 		for(int j=0;j<genres.size();j++) {
	 			String hashId = "mg"+ genres.get(j).getAsString();
	 			String genreName = apiDao.selectGenreName(hashId);
	 			genresList.add(genreName);
	 		}
	 		movie.setGenreIds(genresList);
	 		movie.setMovieId(movieInfo.get("id").getAsString());
	 		movie.setTitle(movieInfo.get("title").getAsString());
	 		//movie.setOriginalTitle(movieInfo.get("original_title").getAsString());
	 		//movie.setOverview(movieInfo.get("overview").getAsString());
	 		try {
	 			movie.setPosterPath(movieInfo.get("poster_path").getAsString());
	 		}catch (UnsupportedOperationException e) {
	 			
			}
	 		movie.setReleaseDate(movieInfo.get("release_date").getAsString());
	 		movieList.add(movie);
	 	}
	 	return movieList;
	}
	
    public List nowPlayingMovies() throws IOException {
    	List movieList = new ArrayList<ApiMovie>();
    	String query = "now_playing?language=ko-KR&page=1&region=KR";
    	String result = Jsoup.connect(url+query)
				.data("api_key",apiKey)
				.data("resultType", "json")
				.ignoreContentType(true)
				.get()
				.text();
    	movieList = movieList(result);
        return movieList;
    }
    
    //"popular?language=ko-kr&page=1&region=kr"
	public ApiMovie movieDetail(int movieId) throws IOException {
		ApiMovie movie = new ApiMovie();
    	String query = movieId+"?language=ko-kr";
    	String result = Jsoup.connect(url+query)
				.data("api_key",apiKey)
				.data("resultType", "json")
				.ignoreContentType(true)
				.get()
				.text();
    	
    	JsonObject object = (JsonObject)JsonParser.parseString(result);
 		movie.setBackdropPath(object.get("backdrop_path").getAsString());
 		JsonArray genres = object.get("genres").getAsJsonArray();
 		List genresList = new ArrayList<String>();
 		for(int j=0;j<genres.size();j++) {
 			String hashId = "mg"+ genres.get(j).getAsJsonObject().get("id").getAsString();
 			String genreName = apiDao.selectGenreName(hashId);
 			genresList.add(genreName);
 		}
 		movie.setGenreIds(genresList);
 		String engCountry = object.get("origin_country").getAsJsonArray().get(0).getAsString();
 		String korCountry = apiDao.selectKorCountry(engCountry);
 		movie.setOriginCountry(korCountry);
 		movie.setMovieId(object.get("id").getAsString());
 		movie.setTitle(object.get("title").getAsString());
 		movie.setOriginalTitle(object.get("original_title").getAsString());
 		movie.setOverview(object.get("overview").getAsString());
 		movie.setPosterPath(object.get("poster_path").getAsString());
 		movie.setReleaseDate(object.get("release_date").getAsString());
 		movie.setRuntime(object.get("runtime").getAsString());
		return movie;
	}
    
	public List<ApiMovie> searchMovies(String query) throws IOException {
    	List<ApiMovie> movieList = new ArrayList<>();
    	// 요청 URL 구성
    	//'https://api.themoviedb.org/3/search/movie?query=%EC%82%AC%EA%B3%BC&include_adult=true&language=ko-kr&page=1&region=kr'
    	String encoded = URLEncoder.encode(query, "UTF-8");
    	String requestUrl = "https://api.themoviedb.org/3/search/movie";
    	// TMDB API 요청 및 응답
    	String result = Jsoup.connect(requestUrl)
    			.data("query",query)
    			.data("api_key",apiKey)
				.data("resultType", "json")
				.data("language","ko-KR")
				.data("region","kr")
				.ignoreContentType(true)
				.get()
				.text();
    	// 응답 JSON 파싱
    	System.out.println("서비스 ="+result);
    	movieList = movieList(result);
        return movieList;
    }
	public List popular() throws IOException {
		List movieList = new ArrayList<ApiMovie>();
    	String result = Jsoup.connect(url+"popular?language=ko-kr&page=1&region=kr")
				.data("api_key",apiKey)
				.data("resultType", "json")
				.ignoreContentType(true)
				.get()
				.text();
    	
    	movieList = movieList(result);
        return movieList;
	}
	//인기
	//'https://api.themoviedb.org/3/movie/top_rated?language=ko-kr&page=1&region=kr'
	//개봉예정
	//--url 'https://api.themoviedb.org/3/movie/upcoming?language=ko-kr&page=1&region=kr' \

	public List topRated() throws IOException {
		List movieList = new ArrayList<ApiMovie>();
    	String result = Jsoup.connect(url +"top_rated?language=ko-kr&page=1&region=kr")
				.data("api_key",apiKey)
				.data("resultType", "json")
				.ignoreContentType(true)
				.get()
				.text();
    	
    	movieList = movieList(result);
        return movieList;
	}

	public List upcomming() throws IOException {
		List movieList = new ArrayList<ApiMovie>();
    	String result = Jsoup.connect(url +"upcoming?language=ko-kr&page=1&region=kr")
				.data("api_key",apiKey)
				.data("resultType", "json")
				.ignoreContentType(true)
				.get()
				.text();
    	
    	movieList = movieList(result);
        return movieList;
	}

	public List recommend(String movieId) throws IOException {
		List movieList = new ArrayList<ApiMovie>();
    	String result = Jsoup.connect(url+movieId+"/recommendations?language=ko-kr&page=1")
				.data("api_key",apiKey)
				.data("resultType", "json")
				.ignoreContentType(true)
				.get()
				.text();
    	
    	movieList = movieList(result);
        return movieList;
	}

}
