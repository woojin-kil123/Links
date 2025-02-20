package kr.co.iei.contents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.contents.model.service.ContentsService;
import kr.co.iei.contents.model.vo.ContentStar;
import kr.co.iei.contents.model.vo.DbMovie;

@Controller
@RequestMapping("/contents")
public class ContentsController {
	@Autowired
	private ContentsService contentsService;
	
	@GetMapping("/movie")
	public String movie() {
		return "contents/movieDetail";
	}

	@GetMapping("/movieList")
	public String movieList() {
		return "contents/movieList";
	}
	@ResponseBody
	@GetMapping("/selectMovie")
	public DbMovie selectMovie(String movieId) {
		DbMovie movie = contentsService.selectMovie(movieId);
		return movie;
	}
	
	@ResponseBody
	@PostMapping("/insertMovie")
	public int insertMovie(DbMovie movie) {
		int result = contentsService.insertMovie(movie);
		return result;
	}
	
	@ResponseBody
	@GetMapping("/insertRating")
	public int insertRating(ContentStar cs) {
		System.out.println(cs.getContentNo());
		System.out.println(cs.getMemberId());
		System.out.println(cs.getStarpoint	());
		
		int result = contentsService.insertRating(cs);
		return result;
	}
	
}

