package kr.co.iei.contents.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.iei.contents.model.service.ApiService;
import kr.co.iei.contents.model.vo.ApiMovie;


@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private ApiService apiService;
	
	 @ResponseBody
	 @GetMapping(value="/movie")
	    public List nowPlayingMovies() {
		 	List movieList = null;
			try {
				movieList = apiService.nowPlayingMovies();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(movieList.toString());
			return movieList;
	}
    @GetMapping(value="/movieDetail")
    public String MovieDetail(int movieId, Model model ) {
		ApiMovie movie = null;
		try {
			movie = apiService.movieDetail(movieId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("movie",movie);
        return "contents/movie";
	}
	/*
	@ResponseBody
	@GetMapping("/insertref")
	public String insertRef() {
		apiService.insertCountry();
		return "redirect:/contents/movieList";
	}
	*/
}
