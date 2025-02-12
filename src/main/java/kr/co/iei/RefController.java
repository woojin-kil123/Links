package kr.co.iei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ref")
public class RefController {

	@GetMapping("/ref1")
	private String ref1() {
		return "ref/photoTable";
	}
	@GetMapping("/ref2")
	private String ref2() {
		return "ref/intro";
	}
	@GetMapping("/ref3")
	private String ref3() {
		return "ref/blogList";
	}
	@GetMapping("/ref4")
	private String ref4() {
		return "ref/blogDetails";
	}
	@GetMapping("/ref5")
	private String ref5() {
		return "ref/contact";
	}
	@GetMapping("/ref6")
	private String ref6() {
		return "ref/elements";
	}
}
