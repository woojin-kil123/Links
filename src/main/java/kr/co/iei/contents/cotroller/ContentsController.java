package kr.co.iei.contents.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contents")
public class ContentsController {
	
	@GetMapping("/movieList")
	public String movieList() {
		return "contents/movieList";
	}
}
