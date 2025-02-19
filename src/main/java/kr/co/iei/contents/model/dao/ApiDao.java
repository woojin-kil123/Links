package kr.co.iei.contents.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApiDao {
	@Autowired
	private JdbcTemplate jdbc;

	public int insertGenres(String hashId, String hashName) {
		String query = "insert into hashtag values(hashtag_seq.nextval, ?,?)";
		Object[] params = {hashId, hashName};
		int result = jdbc.update(query, params);
		return result;
	}

	public int insertCountry(String eng, String ko) {
		String query = "insert into country values(?,?)";
		Object[] params = {eng, ko};
		int result = jdbc.update(query, params);
		return result;
	}

	public String selectGenreName(String hashId) {
		String query = "select hash_name from hashtag where hash_id=?";
		Object[] params = {hashId};
		String name = jdbc.queryForObject(query,String.class,params);
		return name;
	}
	
	
}
