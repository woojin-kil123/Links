package kr.co.iei.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.iei.api.model.service.ApiService;
import kr.co.iei.contents.model.vo.ApiMovie;
import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private ApiService apiService;
	@Value("${company.api.key}")
	private String  serviceKey;
	@Value("${company.api.url}")
	private String url;
	
	 @ResponseBody
	 @GetMapping(value="/nowPlaying")
	    public List nowPlayingMovies() {
		 	List movieList = null;
			try {
				movieList = apiService.nowPlayingMovies();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movieList;
	}
    @GetMapping(value="/movieDetail")
    public String MovieDetail(int movieId, Model model ) {
		ApiMovie movie = null;
		try {
			movie = apiService.movieDetail(movieId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("movie",movie);
        return "contents/movieDetail";
	}
    @GetMapping("/searchMovie")
    public String searchMovie(@RequestParam(required = false) String query, Model model) {
        model.addAttribute("query", query);
        return "search/searchMovie";
    }
    @ResponseBody
    @GetMapping("/searchResult")
    public List searchResult(String query, Model model) {
    	List movieList = null;
    	
		try {
			movieList = apiService.searchMovies(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return movieList;
    }
    @ResponseBody
	@GetMapping("/popular")
    public List trendMovies() {
	 	List movieList = null;
		try {
			movieList = apiService.popular();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
	}
    @ResponseBody
	@GetMapping("/topRated")
    public List topRated() {
	 	List movieList = null;
		try {
			movieList = apiService.topRated();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
	}
    @ResponseBody
	@GetMapping("/upcomming")
    public List upcomming() {
	 	List movieList = null;
		try {
			movieList = apiService.upcomming();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
	}
    @ResponseBody
   	@GetMapping("/recommend")
       public List recommend(String movieId) {
   	 	List movieList = null;
   		try {
   			movieList = apiService.recommend(movieId);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return movieList;
   	}
    
    @ResponseBody
    @GetMapping(value = "/check")
    public String heck(String companyNo) {
    	String companyName = "";
   		try {
   			String result =Jsoup.connect(url)
   			.data("key",serviceKey)
   			.data("q",companyNo)
   			.data("type","json")
   			.ignoreContentType(true)
   			.get()
   			.text();
   		System.out.println(result);
   		JsonObject object=(JsonObject)JsonParser.parseString(result);
   		JsonArray items = object.get("items").getAsJsonArray();
   		JsonObject company = items.get(0).getAsJsonObject();
   		companyName= company.get("company").getAsString();

   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   	return companyName;
    }
    @Autowired
    private EmailSender emailSender;
    @GetMapping(value="/email")
   	public String mail() {
   		return "member/joinFrm";
   	}
    @PostMapping(value="/sendMail")
   	public String sendMail(String emailTitle, String receiver, String emailContent) {
   		System.out.println("제목: "+emailTitle);
   		System.out.println("받는사람: "+receiver);
   		System.out.println("내용: "+emailContent);
   		emailSender.sendMail(emailTitle,receiver,emailContent);
   		return "redirect:/api/email";
   	}
   	
   	
   	@ResponseBody  //비동기에 해줌
   	@GetMapping(value="/sendCode")
   	public String sendCode(String receiver) {
   		//인증메일 제목 생성
   		String emailTitle = "links 인증메일입니다.";
   		//인증 메일용 인증코드 생성
   		Random r = new Random();
   		StringBuffer sb= new StringBuffer(); //문자열 처리해줌 (가변문자열)
   		for(int i=0; i<6; i++) {
   			//숫자(0~9) : r.nextInt(10)
   			//대문자(A~Z) : r.nextInt(26)+65
   			//소문자(a~z) : r.nextInt(26)+97
   			
   			int flag = r.nextInt(3);  //0,1,2 >> 숫자,대,소 어떤걸 사용할지 조차 랜덤으로 결정
   			
   			if(flag == 0) {
   				int randomCode = r.nextInt(10);
   				sb.append(randomCode);
   			}else if(flag == 1) {
   				char randomCode = (char)(r.nextInt(26)+65);
   				sb.append(randomCode);
   				
   			}else {
   				char randomCode = (char)(r.nextInt(26)+97);
   				sb.append(randomCode);
   			}
   		}//for
   		String emailContent = "<h1>안녕하세요. links 입니다.</h1>"
   								+"<h3>인증번호는 "
   								+"[<span style='color:red;'>"
   								+sb.toString()
   								+"</span>]"
   								+"입니다.</h3>";
   		emailSender.sendMail(emailTitle, receiver, emailContent);
   		System.out.println(sb.toString());
   		return sb.toString();
   	}
}
