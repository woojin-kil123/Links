package kr.co.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.admin.vo.ReportRowMapper;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberRowMapper;

@Repository
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private ReportRowMapper reportRowMapper;
    @Autowired
    private MemberRowMapper memberRowMapper;

    public List selectReportReason(String memberId) {
        String query = "SELECT * FROM REPORT WHERE REPORTED_MEMBER_ID = ? ";
        Object[] params = {memberId};
        List list = jdbc.query(query, reportRowMapper,params );
        return list;
        
    }
    
    public Member selectWarningLevel(String memberId) {
    	String checkWarningLevelQuery = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
        Object[] params = {memberId};
        List warningLevel = jdbc.query(checkWarningLevelQuery, memberRowMapper, params);
        if(warningLevel.isEmpty()) {
        	return null;
        }else {
        	Member m = (Member)warningLevel.get(0);
        	return m;
        }
    }
}
