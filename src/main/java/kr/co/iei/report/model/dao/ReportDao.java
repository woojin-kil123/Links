package kr.co.iei.report.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberRowMapper;
import kr.co.iei.report.vo.ReportRowMapper;

@Repository
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private ReportRowMapper reportRowMapper;
    @Autowired
    private MemberRowMapper memberRowMapper;

    public List selectReportReason(int memberNo) {
        String query = "SELECT * FROM REPORT WHERE REPORTED_MEMBER_NO = ? ";
        Object[] params = {memberNo};
        List list = jdbc.query(query, reportRowMapper,params );
        return list;
        
    }
    
    public Member selectWarningLevel(int memberNo) {
    	String checkWarningLevelQuery = "SELECT WANING_LEVEL FROM MEMBER WHERE MEMBER_NO = ?";
        Object[] params = {memberNo};
        List warningLevel = jdbc.query(checkWarningLevelQuery, memberRowMapper, params);
        if(warningLevel.isEmpty()) {
        	return null;
        }else {
        	Member m = (Member)warningLevel.get(0);
        	return m;
        }
    }
}
