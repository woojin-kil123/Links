package kr.co.iei.etc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class MovieSearchController {

    @GetMapping("/searchMovie")
    public String searchMovie(@RequestParam(required = false) String query, Model model) {
        model.addAttribute("query", query); // 검색어를 Model에 추가
        return "search/searchMovie"; // templates/search/searchMovie.html 로 이동
    }
}
