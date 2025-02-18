package kr.co.iei.news.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.news.model.service.NewsService;
import kr.co.iei.news.model.vo.News;
import kr.co.iei.news.model.vo.NewsFile;
import kr.co.iei.news.model.vo.NewsListData;
import kr.co.iei.util.FileUtils;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value="/news")

public class NewsController {
	@Autowired
	private NewsService newsService;
	
	@Value("${file.root}")
	private String root;
	
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping(value="/list")
	public String newsList(Model model, int reqPage) {
		NewsListData nld = newsService.selectNewsList(reqPage);
		model.addAttribute("list",nld.getList());
		model.addAttribute("pageNavi",nld.getPageNavi());
		return "news/list";
	}
	
	@GetMapping(value="/writeFrmEditor")
	public String newsWriteFrm() {
		return "news/writeFrmEditor";
	}
	
	@PostMapping(value="/write")
	public String newsWrite(News n, MultipartFile[] upfile, Model model) {
		List<NewsFile> fileList = new ArrayList<NewsFile>();
		if(!upfile[0].isEmpty()) {
			String savepath = root+"/news/";
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				NewsFile newsFile = new NewsFile();
				newsFile.setFilename(filename);
				newsFile.setFilepath(filepath);
				fileList.add(newsFile);
			}
		}
		int result = newsService.insertNews(n, fileList);
		model.addAttribute("title", "작성 완료");
		model.addAttribute("text", "소식이 등록되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/news/list?reqPage=1");
		return "common/msg";
	}


	@GetMapping(value="/view")
	public String selectOneNews(int newsNo, String check, Model model, @SessionAttribute(required = false) Member member) {
		int memberNo = 0;
		if(member != null) {
			memberNo = member.getMemberNo();
		}
		News n = newsService.selectOneNews(newsNo, memberNo, check);
		if(n == null) {
			model.addAttribute("title","소식 조회 실패");
			model.addAttribute("text","존재하지 않는 소식입니다.");
			model.addAttribute("icon","warning");
			model.addAttribute("loc","/news/list?reqPage=1");
			return "common/msg";
		}else {
			model.addAttribute("n",n);
			return "news/view";
		}
	}

	@GetMapping(value="/filedown")
	public void filedown(NewsFile newsFile, HttpServletResponse response) {
		String savepath = root+"/news/";
		fileUtils.downloadFile(savepath, newsFile.getFilename(), newsFile.getFilepath(),response);
	}
	
	@GetMapping(value="/delete")
	public String deleteNews(int newsNo, Model model) {
		List<NewsFile> list = newsService.deleteNews(newsNo);
		String savepath = root+"/news/";
		for(NewsFile file : list) {
			File delFile = new File(savepath+file.getFilepath());
			delFile.delete();
		}
		model.addAttribute("title","소식 삭제 완료");
		model.addAttribute("text","소식이 삭제 되었습니다.");
		model.addAttribute("icon","success");
		model.addAttribute("loc","/news/list?reqPage=1");
		return "common/msg";
	}
	
	@GetMapping(value="/updateFrm")
	public String updateFrm(int newsNo, Model model) {
		News n = newsService.selectOneNews(newsNo, 0, "1");
		model.addAttribute("n",n);
		return "news/updateFrm";
	}
	
	@PostMapping(value="/update")
	public String update(News n, MultipartFile[] upfile, int[] delFileNo) {
		List<NewsFile> fileList = new ArrayList<NewsFile>();
		String savepath = root+"/news/";
		if(!upfile[0].isEmpty()) {
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				NewsFile newsFile = new NewsFile();
				newsFile.setFilename(filename);
				newsFile.setFilepath(filepath);
				newsFile.setNewsNo(n.getNewsNo());
				fileList.add(newsFile);
			}
		}
		List<NewsFile> delFileList = newsService.updateNews(n, fileList, delFileNo);
		for(NewsFile newsFile : delFileList) {
			File delFile = new File(savepath+newsFile.getFilepath());
			delFile.delete();
		}
		return "redirect:/news/view?newsNo="+n.getNewsNo()+"&check=1";
	}
	
	@ResponseBody
	@PostMapping(value="/editorImage", produces = "plain/text;charset=utf-8")
	public String editorImage(MultipartFile upfile) {
		String savepath = root+"/news/editor/";
		String filepath = fileUtils.upload(savepath, upfile);
		return filepath;
	}
}
