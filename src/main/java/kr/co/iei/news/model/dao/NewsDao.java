package kr.co.iei.news.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.news.model.vo.News;
import kr.co.iei.news.model.vo.NewsFile;
import kr.co.iei.news.model.vo.NewsFileRowMapper;
import kr.co.iei.news.model.vo.NewsRowMapper;

@Repository
public class NewsDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private NewsRowMapper newsRowMapper;
	@Autowired
	private NewsFileRowMapper newsFileRowMapper;
	
	public List selectNewsList(int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from news order by news_no desc)n) where rnum between ? and ?";
		Object[] params = {start, end};
		List list = jdbc.query(query, newsRowMapper, params);
		return list;
	}

	public int selectNewsTotalCount() {
		String query = "select count(*) from news";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public int insertNews(News n) {
		String query = "insert into news values(?,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {n.getNewsNo(),n.getNewsTitle(),n.getMemberId(),n.getNewsContent()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int newNewsNo() {
		String query = "select news_seq.nextval from dual";
		int newsNo = jdbc.queryForObject(query, Integer.class);
		return newsNo;
	}

	public int insertNewsFile(NewsFile newsFile) {
		String query = "insert into news_file values(news_file_seq.nextval,?,?,?)";
		Object[] params = {newsFile.getNewsNo(), newsFile.getFilename(), newsFile.getFilepath()};
		int result = jdbc.update(query, params);
		return result;
	}

	public News selectOneNews(int newsNo) {
		String query = "select * from news where news_no = ?";
		Object[] params = {newsNo};
		List list = jdbc.query(query, newsRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			News n = (News)list.get(0);
			return n;
		}
	}

	public int updateReadCount(int newsNo) {
		String query = "update news set news_read_count = news_read_count + 1 where news_no = ?";
		Object[] params = {newsNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public List selectNewsFile(int newsNo) {
		String query = "select * from news_file where news_no = ?";
		Object[] params = {newsNo};
		List list = jdbc.query(query, newsFileRowMapper, params);
		return list;
	}

	public int deleteNews(int newsNo) {
		String query = "delete from news where news_no = ?";
		Object[] params = {newsNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateNews(News n) {
		String query = "update news set news_title = ?, news_content = ? where news_no = ?";
		Object[] params = {n.getNewsTitle(),n.getNewsContent(),n.getNewsNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public NewsFile selectOneNewsFile(int newsFileNo) {
		String query = "select * from news_file where news_file_no = ?";
		Object[] params = {newsFileNo};
		List list = jdbc.query(query, newsFileRowMapper, params);
		NewsFile newsFile = (NewsFile)list.get(0);
		return newsFile;
	}

	public int deleteNewsFile(int newsFileNo) {
		String query = "delete from news_file where news_file_no = ?";
		Object[] params = {newsFileNo};
		int result = jdbc.update(query,params);
		return result;
	}

}
