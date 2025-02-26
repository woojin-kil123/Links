package kr.co.iei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	/*
	 * @GetMapping("/ref/sample") public String sample() { return "ref/sample"; }
	 */
}
