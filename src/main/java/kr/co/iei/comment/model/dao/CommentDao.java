package kr.co.iei.comment.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.comment.model.vo.CommentRowMapper;

@Repository
public class CommentDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CommentRowMapper commentRowMapper;
}
