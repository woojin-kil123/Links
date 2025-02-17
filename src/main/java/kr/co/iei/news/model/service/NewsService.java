package kr.co.iei.news.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.news.model.dao.NewsDao;
import kr.co.iei.news.model.vo.News;
import kr.co.iei.news.model.vo.NewsFile;
import kr.co.iei.news.model.vo.NewsListData;

@Service
public class NewsService {
	@Autowired
	private NewsDao newsDao;

	public NewsListData selectNewsList(int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		List list = newsDao.selectNewsList(start, end);
		int totalCount = newsDao.selectNewsTotalCount();
		int totalPage = totalCount / numPerPage;
		if (totalCount % numPerPage != 0) {
			totalPage += 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		String pageNavi = "<ul class='pagination circle-style'>";

		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/news/list?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			pageNavi += "<li>";
			if(pageNo == reqPage) {
				pageNavi += "<a class='page-item active-page' href='/news/list?reqPage="+pageNo+"'>";
			}else {
				pageNavi += "<a class='page-item' href='/news/list?reqPage="+pageNo+"'>";
			}
			pageNavi += pageNo;
			pageNavi += "</a></li>";
			pageNo++;

			if(pageNo > totalPage) {
				break;
			}
		}

		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/news/list?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";

		NewsListData nld = new NewsListData(list, pageNavi);
		return nld;
	}

	@Transactional
	public int insertNews(News n, List<NewsFile> fileList) {
		int newsNo = newsDao.newNewsNo();
		n.setNewsNo(newsNo);
		int result = newsDao.insertNews(n);
		for(NewsFile newsFile : fileList) {
			newsFile.setNewsNo(newsNo);
			result += newsDao.insertNewsFile(newsFile);
		}
		return result;
	}
	
	@Transactional
	public News selectOneNews(int newsNo, int memberNo, String check) {
		News n = newsDao.selectOneNews(newsNo);
		if(n != null) {
			if(check == null) {
				int result = newsDao.updateReadCount(newsNo);				
			}

			List fileList = newsDao.selectNewsFile(newsNo);
			n.setFileList(fileList);
		}
		return n;
	}
	
	@Transactional
	public List<NewsFile> deleteNews(int newsNo) {
		List list = newsDao.selectNewsFile(newsNo);
		int result = newsDao.deleteNews(newsNo);
		return list;
	}
	
	@Transactional
	public List<NewsFile> updateNews(News n, List<NewsFile> fileList, int[] delFileNo) {
		List<NewsFile> delFileList = new ArrayList<NewsFile>();
		int result = newsDao.updateNews(n);
		for(NewsFile newsFile : fileList) {
			newsFile.setNewsNo(n.getNewsNo());
			result += newsDao.insertNewsFile(newsFile);
		}
		if(delFileNo != null) {
			for(int newsFileNo : delFileNo) {
				NewsFile newsFile = newsDao.selectOneNewsFile(newsFileNo);
				delFileList.add(newsFile);
				result += newsDao.deleteNewsFile(newsFileNo);
			}
		}
		return delFileList;
	}
}
