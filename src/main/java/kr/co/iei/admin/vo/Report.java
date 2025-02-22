package kr.co.iei.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Report {
	private String writeNo;
	private String reporterMemberId;
	private String reportedMemberId;
	private String reportReason;
	private String reportYn;
	private String reg_date;
}
