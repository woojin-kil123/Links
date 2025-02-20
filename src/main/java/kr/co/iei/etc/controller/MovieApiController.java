package kr.co.iei.etc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/movie")
public class MovieApiController {
	
	@Value("${tmdb.api.key}")
	private String apiKey;
	
	@Value("${tmdb.api.url}")
	private String apiUrl;
	
	@Value("${tmdb.api.language}")
	private String language;
	
	private final RestTemplate restTemplate;
	
	public MovieApiController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	

    @GetMapping("/search")
    public ResponseEntity<?> searchMovie(@RequestParam String query) {
        String requestUrl = String.format("%s?api_key=%s&query=%s&language=%s", 
                                            apiUrl, apiKey, query, language);
        // 🔥 디버깅: 요청 URL 로그 출력
        System.out.println("TMDB API 요청 URL: " + requestUrl);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);

            // 🔥 디버깅: 응답 본문 로그 출력
            System.out.println("TMDB API 응답: " + response.getBody());

            // JSON 파싱 후 객체로 반환
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>(){});
            return ResponseEntity.ok(jsonMap);
        } catch (Exception e) {
            e.printStackTrace(); // 오류 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영화 검색 중 오류 발생");
        }
    }
}
