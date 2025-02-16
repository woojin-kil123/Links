package kr.co.iei.contents.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {
	private String apiUrl = "https://api.themoviedb.org/3/movie/";
	//private String apiUrl = "https://api.themoviedb.org/3/movie/changes?page=1";
	private String apiKey = "7bf2a455f3dd6b2ff509cd182bb2888f";
	
	@Autowired
	private RestTemplate restTemplate;
	
    public String getMovies() {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl+"now_playing?language=ko-KR&page=1&region=KR")
                .queryParam("api_key", apiKey)
                .toUriString();

        // REST API 호출
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieDetails(int movieId) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl +movieId)
                .queryParam("api_key", apiKey)
                .toUriString();

        // REST API 호출
        return restTemplate.getForObject(url, String.class, movieId);
    }

	
}
