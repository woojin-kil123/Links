package kr.co.iei.contents.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contents")
public class ContentsController {
	
	@GetMapping("/movieList")
	public String movieList() {
		return "contents/movieList";
	}

}
