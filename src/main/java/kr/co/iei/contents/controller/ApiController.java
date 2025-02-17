package kr.co.iei.contents.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.iei.contents.model.service.ApiService;


@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private ApiService apiService;
	
	 @ResponseBody
	 @GetMapping("/movie")
	    public Object getPopularMovies() {
		 	String result = apiService.getMovies();
		 	return result;
	    }
	@ResponseBody
    @GetMapping("/movieId")
    public String getMovieDetails(int movieId) {
        return apiService.getMovieDetails(movieId);
        
	    }
}
